package com.sakshi.coaching.DAO;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakshi.coaching.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDAO(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUser(User user) {
        String s = "insert into users(id,username,password,role) values(?,?,?,?)";
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        jdbcTemplate.update(s, user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public List<User> getAllUsers() {
        String s = "select * from users";
        return jdbcTemplate.query(s, userRowMapper);
    }

    public User getUserByUsername(String username) {
        String s = "select * from users where username=?";
        return jdbcTemplate.queryForObject(s, userRowMapper, username);
    }

    public User getUserByUserId(int userId) {
        String s = "select * from users where id=?";
        return jdbcTemplate.queryForObject(s, userRowMapper, userId);
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
    };

}
