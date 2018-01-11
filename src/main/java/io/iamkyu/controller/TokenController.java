package io.iamkyu.controller;

import io.iamkyu.domain.AccessToken;
import io.iamkyu.dto.UserCredentials;
import io.iamkyu.exception.BadCredentialsException;
import io.iamkyu.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author Kj Nam
 */
@Controller
public class TokenController {

    @Autowired private TokenService tokenService;

    @PostMapping("/oauth/token")
    public ResponseEntity<AccessToken> allocateToken(Map<String, String> parameters) throws BadCredentialsException {

        String username = parameters.get("username");
        String password = parameters.get("password");

        UserCredentials userCredentials = new UserCredentials(username, password);
        AccessToken accessToken = tokenService.allocateToken(userCredentials);

        return getResponse(accessToken);
    }


    private ResponseEntity<AccessToken> getResponse(AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
    }
}
