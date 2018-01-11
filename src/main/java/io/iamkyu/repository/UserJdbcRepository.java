package io.iamkyu.repository;

import io.iamkyu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kj Nam
 */
@Repository
public class UserJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT no, name, password FROM user WHERE name = ?",
                        new Object[]{userName}, this.userRowMapper)
        );
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getInt("no"), rs.getString("name"), rs.getString("password")
    );
}
