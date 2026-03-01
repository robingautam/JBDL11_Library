package org.gfg.JBDL11_DigitalLibrary.Repository;

import org.gfg.JBDL11_DigitalLibrary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_USER = "INSERT INTO users (user_name, user_email, user_password, user_phone, user_address, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE user_email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            user.setUserPhone(rs.getString("user_phone"));
            user.setUserAddress(rs.getString("user_address"));
            user.setCreatedAt(rs.getTimestamp("created_at").getTime());
            return user;
        }
    };

    // Create a new user
    public User createUser(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        jdbcTemplate.update(INSERT_USER,
            user.getUserName(),
            user.getUserEmail(),
            user.getUserPassword(),
            user.getUserPhone(),
            user.getUserAddress(),
            new Timestamp(currentTimeMillis)
        );
        return user;
    }

    // Get user by ID
    public User getUserById(Long userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new Object[]{userId}, userRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    // Get user by email
    public User getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[]{email}, userRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS, userRowMapper);
    }
}
