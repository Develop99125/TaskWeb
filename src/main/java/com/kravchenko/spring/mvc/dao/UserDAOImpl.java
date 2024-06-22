package com.kravchenko.spring.mvc.dao;

import com.kravchenko.spring.mvc.entity.Role;
import com.kravchenko.spring.mvc.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO{

    private final SessionFactory sessionFactory;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory, PasswordEncoder bCryptPasswordEncoder) {
        this.sessionFactory = sessionFactory;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from users", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean createNewUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        if(isExist(user.getUsername())) return false;
        String result = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(result);
        session.save(user);
        createRoleForUser(user.getUsername());
        return true;
    }

    @Override
    @Transactional
    public User getUserByName(String s) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from users where username = :username", User.class);
        query.setParameter("username", s);
        return query.getSingleResult();
    }

    private boolean isExist(String username){
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(u.username) from users u where username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() != 0;
    }

    private void createRoleForUser(String username){
        Session session = sessionFactory.getCurrentSession();
        session.save(new Role(username, "ROLE_USER"));
    }
}
