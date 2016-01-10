/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.dao;

import com.akursat.webapp.model.Authorities;
import com.akursat.webapp.model.Users;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author akursat
 */

@Repository
public class UserDaoImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(Users user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from Users").list();
    }

    @Override
    public void deleteUser(String username) {
        Users user = (Users) sessionFactory.getCurrentSession().load(
                Users.class, username);
        if (null != user) {
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }
    
    @Override
    @Transactional
    public boolean emailAlreadyExists(String email) {

        int count = ((Long) this.sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Users u WHERE u.email=:emailparam")
                .setParameter("emailparam", email)
                .uniqueResult()).intValue();

        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }
    
    @Override
    @Transactional
    public boolean usernameAlreadyExists(String username) {
        int count = ((Long) this.sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Users u WHERE u.username=:usernameparam")
                .setParameter("usernameparam", username)
                .uniqueResult()).intValue();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    

}
