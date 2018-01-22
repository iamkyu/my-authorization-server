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
        sql.append("SELECT no, client_id, client_secret");
        sql.append("FROM oauth_client_details ");
        sql.append("WHERE  client_id = ?");

        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql.toString(),
                new Object[]{clientId}, this.clientRowMapper)
        );
    }

    private RowMapper<Client> clientRowMapper = (rs, rowNum) -> new Client(
            rs.getLong("no"),
            rs.getString("client_id"),
            rs.getString("client_secret")
    );
}

