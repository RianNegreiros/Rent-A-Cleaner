package com.github.riannegreiros.ExpressCleaning.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.github.riannegreiros.ExpressCleaning.core.models.Service;
import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/services")
public class ServiceController {

  @Autowired
  private ServiceRepository repository;

  @GetMapping
  public ModelAndView findAll() {
    var modelAndView = new ModelAndView("admin/service/list");

    modelAndView.addObject("services", repository.findAll());

    return modelAndView;
  }

  @GetMapping("/register")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/service/form");

    modelAndView.addObject("service", new Service());

    return modelAndView;
  }

  @PostMapping("/register")
  public String register(Service service) {
    repository.save(service);

    return "redirect:/admin/services";
  }

  @GetMapping("/{id}/delete")
  public String excluir(@PathVariable Long id) {
    repository.deleteById(id);

    return "redirect:/admin/services";
  }

}
