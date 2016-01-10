/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.service;

import com.akursat.webapp.dao.UserDAO;
import com.akursat.webapp.model.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author akursat
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(Users user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }
    
   
    

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean emailAlreadyExists(String email) {
        return userDAO.emailAlreadyExists(email);
    }

    public boolean usernameAlreadyExists(String username) {
        return userDAO.usernameAlreadyExists(username);
    }

}
