package org.example.dao;



import org.example.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();

    void insert(String name, String id) throws Exception;
}
