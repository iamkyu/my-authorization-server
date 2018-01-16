package io.iamkyu.repository;

import io.iamkyu.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kj Nam
 */
@Repository
public class ClientJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Client> findByClientId(String clientId) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove ");
        sql.append("FROM oauth_client_details ");
        sql.append("WHERE  client_id = ?");

        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql.toString(),
                new Object[]{clientId}, this.clientRowMapper)
        );
    }

    private RowMapper<Client> clientRowMapper = (rs, rowNum) -> new Client(
            rs.getString("client_id"),
            rs.getString("client_secret")
    );
}

