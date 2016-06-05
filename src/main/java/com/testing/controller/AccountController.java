package com.testing.controller;

import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.User;
import com.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import java.io.PrintWriter;

/**
 * Created by Study on 25.05.2016.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    // Return registration page
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView handleRequestRegistration() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String registrationForm(
                @RequestParam(value = "nickName") String nickName,
                @RequestParam(value = "password") String password,
                @RequestParam(value = "firstName") String firstName,
                @RequestParam(value = "lastName") String lastName
    ) throws Exception {


        boolean isRegistered = userService.Register(nickName, new ShaPasswordEncoder().encodePassword(password, null), firstName, lastName);

        return "{\"success\":" + isRegistered + "}";
    }
}
