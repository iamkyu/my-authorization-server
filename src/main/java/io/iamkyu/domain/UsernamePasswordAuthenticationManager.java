package io.iamkyu.domain;

import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) {

        authentication.setAuthenticated(true);

        return authentication;
    }
}
