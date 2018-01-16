package io.iamkyu.controller;

import io.iamkyu.domain.AccessToken;
import io.iamkyu.domain.Client;
import io.iamkyu.dto.UserCredentials;
import io.iamkyu.exception.BadCredentialsException;
import io.iamkyu.exception.BadRequestException;
import io.iamkyu.service.ClientService;
import io.iamkyu.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author Kj Nam
 */
@Controller
public class TokenController {

    @Autowired private TokenService tokenService;

    @Autowired private ClientService clientService;

    @PostMapping("/oauth/token")
    public ResponseEntity<AccessToken> allocateToken(@RequestHeader(name = "Authorization") String authorization, Map<String, String> parameters) throws BadCredentialsException {

        if (!authorization.startsWith("Basic") || authorization.length() < 7) {
            throw new BadRequestException("Authorization 헤더가 올바르지 않습니다.");
        }


        String clientAuthorization = authorization.substring(6);
        String decode = new String(Base64Utils.decodeFromString(clientAuthorization));

        String[] split = decode.split(":");
        Client client = clientService.loadClientByClientId(split[0]);

        String username = parameters.get("username");
        String password = parameters.get("password");

        UserCredentials userCredentials = new UserCredentials(username, password);
        AccessToken accessToken = tokenService.allocateToken(client, userCredentials);

        return getResponse(accessToken);
    }

    private ResponseEntity<AccessToken> getResponse(AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
    }
}
