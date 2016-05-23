package com.testing.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    // Start page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleRequestStart() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("../index");

        return model;
    }
}
