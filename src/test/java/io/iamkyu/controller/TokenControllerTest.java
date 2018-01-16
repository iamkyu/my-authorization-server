package io.iamkyu.controller;

import io.iamkyu.domain.Client;
import io.iamkyu.domain.User;
import io.iamkyu.service.ClientService;
import io.iamkyu.service.TokenService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kj Nam
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TokenController.class)
public class TokenControllerTest {

    @Autowired private WebApplicationContext context;

    @MockBean private TokenService tokenService;

    @MockBean private ClientService clientService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Ignore("흐름만 만들어둔 테스트. 개선 필요")
    @Test
    public void 유효한_토큰_요청을_보낸다() throws Exception {
        //given
        Client client = new Client("client-id", "client-secret");
        User user = new User(1L, "foo", "bar");

        String concat = String.format("%s:%s", client.getClientId(), client.getClientSecret());
        String encodedBasicHeader = new String(Base64Utils.encodeToString(concat.getBytes()));

        //when then
        this.mvc.perform(post("/oauth/token")
                    .header("Authorization", "Basic " + encodedBasicHeader)
                    .param("grant_type", "password")
                    .param("username", user.getName())
                    .param("password", user.getPassword()))
                .andExpect(status().isOk())
                // .andExpect(content().json("{\"active\":true}"))
                .andDo(print());
    }
}