package io.iamkyu.service;

import io.iamkyu.domain.Client;
import io.iamkyu.exception.InvalidClientException;
import io.iamkyu.repository.ClientJdbcRepository;
import org.springframework.stereotype.Service;

/**
 * @author Kj Nam
 */
@Service
public class ClientService {

    private ClientJdbcRepository clientJdbcRepository;

    public ClientService(ClientJdbcRepository clientJdbcRepository) {
        this.clientJdbcRepository = clientJdbcRepository;
    }

    public Client loadClientByClientId(String clientId) {
        return clientJdbcRepository.findByClientId(clientId)
                .orElseThrow(() -> new InvalidClientException("invalid client id"));
    }
}
