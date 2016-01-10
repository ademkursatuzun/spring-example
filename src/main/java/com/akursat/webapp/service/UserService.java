/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.service;

import com.akursat.webapp.model.Users;
import java.util.List;

/**
 *
 * @author akursat
 */
public interface UserService {

    public void addUser(Users user);

    public List<Users> getAllUsers();

    public void deleteUser(String username);

    public boolean emailAlreadyExists(String email);

    public boolean usernameAlreadyExists(String username);
}
