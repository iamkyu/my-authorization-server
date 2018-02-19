package io.iamkyu.api;

import io.iamkyu.api.TokenController;
import io.iamkyu.domain.AccessToken;
import io.iamkyu.domain.Authentication;
import io.iamkyu.domain.Client;
import io.iamkyu.domain.RefreshToken;
import io.iamkyu.domain.UsernamePasswordAuthentication;
import io.iamkyu.domain.UsernamePasswordAuthenticationManager;
import io.iamkyu.service.ClientService;
import io.iamkyu.service.TokenService;
import org.junit.Before;
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

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
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

    @MockBean UsernamePasswordAuthenticationManager authenticationManager;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 유효한_토큰_요청을_보낸다() throws Exception {
        //given
        Client client = new Client(1L, "client-id", "client-secret");
        Authentication authentication = new UsernamePasswordAuthentication("foo", "bar");
        AccessToken accessToken = generateToken();

        given(clientService.loadClientByClientId(client.getClientId())).willReturn(client);
        // TODO allocateToken(client, authentication) 을 given 조건으로 하면 테스트시 다른 객체로 인식해서 기대대로 동작을 안함
        given(tokenService.allocateToken(any(), any())).willReturn(accessToken);

        //when then
        this.mvc.perform(post("/oauth/token")
                    .header("Authorization", "Basic " + generateEncodedBasicHeader(client.getClientId(), client.getClientSecret()))
                    .param("grant_type", "password")
                    .param("username", (String) authentication.getPrincipal())
                    .param("password", (String) authentication.getCredentials()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String generateEncodedBasicHeader(String clientId, String secret) {
        String concat = String.format("%s:%s", clientId, secret);
        return new String(Base64Utils.encodeToString(concat.getBytes()));
    }

    private AccessToken generateToken() {
        return new AccessToken(
                UUID.randomUUID().toString(),
                AccessToken.TOKEN_TYPE_BEARER,
                new RefreshToken(UUID.randomUUID().toString()),
                Date.from(Instant.now())
        );
    }
}