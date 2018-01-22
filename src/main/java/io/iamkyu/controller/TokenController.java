package io.iamkyu.controller;

import io.iamkyu.domain.AccessToken;
import io.iamkyu.domain.Authentication;
import io.iamkyu.domain.Client;
import io.iamkyu.domain.UsernamePasswordAuthentication;
import io.iamkyu.domain.UsernamePasswordAuthenticationManager;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Kj Nam
 */
@Controller
public class TokenController {

    @Autowired private TokenService tokenService;

    @Autowired private ClientService clientService;

    @Autowired private UsernamePasswordAuthenticationManager authenticationManager;

    @PostMapping("/oauth/token")
    public ResponseEntity<AccessToken> allocateToken(@RequestHeader(name = "Authorization") String authorization,
                                                     @RequestParam Map<String, String> parameters) throws Exception {

        if (!authorization.startsWith("Basic") || authorization.length() < 7) {
            throw new BadRequestException("Authorization 헤더가 올바르지 않습니다.");
        }

        String clientAuthorization = authorization.substring(6);
        String credential = new String(Base64Utils.decodeFromString(clientAuthorization));

        String[] credentials = credential.split(":");
        Client authorizedClient = clientService.loadClientByClientId(credentials[0]);

        Authentication authentication = new UsernamePasswordAuthentication(parameters.get("username"), parameters.get("password"));
        authenticationManager.authenticate(authentication);

        if (!authentication.isAuthenticated()) {
            // TODO throw exception
        }

        AccessToken accessToken = tokenService.allocateToken(authorizedClient, authentication);

        if (accessToken == null) {
            // TODO throw exception
            throw new Exception("Access token is null");
        }

        return getResponse(accessToken);
    }

    private ResponseEntity<AccessToken> getResponse(AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
    }
}
