package com.kravchenko.spring.mvc.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

@Entity(name = "users")
public class User{
    @Id
    @Size(min = 5, message = "Логин должен состоять как минимум из пяти символов")
    @Column(name = "username")
    private String username;
    @Size(min = 5, message = "Пароль должен состоять как минимум из пяти символов")
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private int enabled = 1;

    public User() {
    }

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
