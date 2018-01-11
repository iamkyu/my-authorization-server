package io.iamkyu.service;

import io.iamkyu.domain.AccessToken;
import io.iamkyu.domain.RefreshToken;
import io.iamkyu.domain.User;
import io.iamkyu.dto.UserCredentials;
import io.iamkyu.exception.BadCredentialsException;
import io.iamkyu.repository.TokenJdbcRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * @author Kj Nam
 */
@Service
public class TokenService {

    private TokenJdbcRepository tokenJdbcRepository;

    private UserService userService;

    public TokenService(TokenJdbcRepository tokenJdbcRepository, UserService userService) {
        this.tokenJdbcRepository = tokenJdbcRepository;
        this.userService = userService;
    }

    public AccessToken allocateToken(UserCredentials userCredentials) throws BadCredentialsException {
        User user = userService.userAuthentication(userCredentials);

        AccessToken accessToken = new AccessToken(
                UUID.randomUUID().toString(),
                AccessToken.TOKEN_TYPE_BEARER,
                new RefreshToken(UUID.randomUUID().toString()),
                Date.from(Instant.now()));

        tokenJdbcRepository.save(user, accessToken);
        return accessToken;
    }
}
