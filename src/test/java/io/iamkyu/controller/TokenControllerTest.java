package io.iamkyu.controller;

import io.iamkyu.domain.AccessToken;
import io.iamkyu.domain.RefreshToken;
import io.iamkyu.dto.UserCredentials;
import io.iamkyu.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kj Nam
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = TokenController.class)
public class TokenControllerTest {

    @Autowired private WebApplicationContext context;

    @MockBean private TokenService tokenService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void requestForToken() throws Exception {
        //given
        UserCredentials userCredentials = new UserCredentials("foo", "fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9");
        AccessToken token = new AccessToken(
                UUID.randomUUID().toString(),
                AccessToken.TOKEN_TYPE_BEARER,
                new RefreshToken(UUID.randomUUID().toString()),
                Date.from(Instant.now()));

        when(tokenService.allocateToken(userCredentials))
                .thenReturn(token);

        //when then
        this.mvc.perform(post("/oauth/token")
                    .param("username", "foo")
                    .param("password", "bar"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}