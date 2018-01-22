package io.iamkyu.service;

import io.iamkyu.domain.Client;
import io.iamkyu.exception.InvalidClientException;
import io.iamkyu.repository.ClientJdbcRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

/**
 * @author Kj Nam
 */
@RunWith(SpringRunner.class)
public class ClientServiceTest {

    @Mock private ClientJdbcRepository clientJdbcRepository;

    private ClientService clientService;

    @Before
    public void setUp() {
        clientService = new ClientService(clientJdbcRepository);
    }

    @Test
    public void 클라이언트_아이디가_유효하면_클라이언트를_반환한다() {
        //given
        String clientId = "client-id";
        Client client = new Client(1L, clientId, "");

        given(clientJdbcRepository.findByClientId(clientId)).willReturn(Optional.of(client));

        //when
        Client found = clientService.loadClientByClientId(clientId);

        //then
        Assert.assertEquals(client, found);
    }

    @Test(expected = InvalidClientException.class)
    public void 클라이언트_아이디가_유효하지_않으면_예외발생() {
        //given
        String invalidId = "foobar";

        given(clientJdbcRepository.findByClientId(invalidId)).willReturn(Optional.empty());

        //when
        clientService.loadClientByClientId(invalidId);

        //then
        fail();
    }
}