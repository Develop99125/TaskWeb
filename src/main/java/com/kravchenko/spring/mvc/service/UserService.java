package com.kravchenko.spring.mvc.service;

import com.kravchenko.spring.mvc.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public boolean createNewUser(User user);
}
