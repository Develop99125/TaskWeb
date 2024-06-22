package com.kravchenko.spring.mvc.dao;

import com.kravchenko.spring.mvc.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public boolean createNewUser(User user);

    public User getUserByName(String s);
}
