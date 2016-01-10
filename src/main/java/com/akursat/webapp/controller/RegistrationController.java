/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.controller;

import com.akursat.webapp.model.Authorities;
import com.akursat.webapp.model.Users;
import com.akursat.webapp.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.SimpleFormController;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akursat
 */
@Controller
public class RegistrationController extends SimpleFormController {

    @Autowired
    UserService userService;

    JsonResponse res = new JsonResponse();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute(value = "users") Users user, BindingResult result,
            @RequestParam("email") String email, @RequestParam("username") String username) {

        System.out.println("XXXXX:" + "user:" + user.getUsername());
        System.out.println("XXXXX:" + "mail:" + user.getEmail());
        System.out.println("XXXXX:" + "pass:" + user.getPassword());
        System.out.println("XXXXX:" + "date:" + user.getBirthday());
        
        user.setEnabled(true);
        Authorities auth = new Authorities(username,"ROLE_USER");
        
        user.setAuthorities(auth);
        auth.setUsers(user);
        userService.addUser(user);
       
        
        System.out.println("XXXXX:" + "user added");
        return "redirect:/login"; //<-----redirect:/registration !! change

    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addUser(@ModelAttribute(value = "users") Users user, BindingResult result) {

        JsonResponse res = new JsonResponse();

        if (userService.emailAlreadyExists(user.getEmail())) {
            System.out.println("XXXXX:" + "email exist!");
            res.setStatus("FAIL1");

        } else if (userService.usernameAlreadyExists(user.getUsername())) {
            System.out.println("XXXXX:" + "username exist!");
            res.setStatus("FAIL2");

        } else {
            res.setStatus("SUCCESS");

        }

        return res;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws ServletException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
