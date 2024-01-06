package com.github.riannegreiros.ExpressCleaning.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.riannegreiros.ExpressCleaning.web.services.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public ModelAndView getAll() {
    var modelAndView = new ModelAndView("admin/user/list");

    modelAndView.addObject("users", service.getAll());

    return modelAndView;
  }
}
