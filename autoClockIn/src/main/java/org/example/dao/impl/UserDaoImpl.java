package org.example.dao.impl;


import org.example.dao.UserDao;
import org.example.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = jdbcTemplate.query("select * from user ", new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public void insert(String name, String id) throws Exception{
        jdbcTemplate.update("insert into user(name, id) values(?,?) ", name, id);
    }
}
