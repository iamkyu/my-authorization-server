package io.iamkyu.service;

import io.iamkyu.domain.Client;
import org.springframework.stereotype.Service;

/**
 * @author Kj Nam
 */
@Service
public class ClientService {

    public Client loadClientByClientId(String clientId) {
        return new Client(clientId, null);
    }
}
