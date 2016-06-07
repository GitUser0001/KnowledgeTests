package com.testing.controller;

import com.testing.model.Test;
import com.testing.model.User;
import com.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Study on 07.06.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ModelAndView handleRequestUserResults(){

        ModelAndView model = new ModelAndView("userResults");

        String userNick = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByNick(userNick);

        model.addObject("user", user);

        return model;
    }
}
