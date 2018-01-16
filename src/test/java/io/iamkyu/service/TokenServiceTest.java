package io.iamkyu.service;

import io.iamkyu.exception.BadCredentialsException;
import io.iamkyu.repository.TokenJdbcRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kj Nam
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenServiceTest {

    private TokenService tokenService;

    @Mock
    private TokenJdbcRepository tokenJdbcRepository;

    @Mock
    private UserService userService;


    @Before
    public void setUp() {
        tokenService = new TokenService(tokenJdbcRepository, userService);
    }

    @Test
    public void 사용자_자격증명이_올바르면_액세스토큰을_얻는다() throws BadCredentialsException {
        // //given
        // UserCredentials userCredentials = new UserCredentials("foo", "fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9");
        // User authenticatedUser = new User(1L, "foo", "fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9");
        //
        // when(userService.userAuthentication(userCredentials))
        //         .thenReturn(authenticatedUser);
        //
        // //when
        // AccessToken accessToken = tokenService.allocateToken(client, userCredentials);
        //
        // //then
        // Assert.assertNotNull(accessToken.getValue());
    }
}