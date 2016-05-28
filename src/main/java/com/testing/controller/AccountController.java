package com.testing.controller;

import com.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


}
