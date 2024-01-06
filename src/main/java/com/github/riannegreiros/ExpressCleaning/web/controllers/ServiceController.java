package com.github.riannegreiros.ExpressCleaning.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ServiceForm;
import com.github.riannegreiros.ExpressCleaning.web.mappers.ServiceMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/services")
public class ServiceController {

  @Autowired
  private ServiceRepository repository;

  @Autowired
  private ServiceMapper mapper;

  @GetMapping
  public ModelAndView findAll() {
    var modelAndView = new ModelAndView("admin/service/list");

    modelAndView.addObject("services", repository.findAll());

    return modelAndView;
  }

  @GetMapping("/register")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/service/form");

    modelAndView.addObject("form", new ServiceForm());

    return modelAndView;
  }

  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("form") ServiceForm form, BindingResult result) {
    if (result.hasErrors()) {
      return "admin/service/form";
    }

    var service = mapper.toModel(form);
    repository.save(service);

    return "redirect:/admin/services";
  }

  @GetMapping("/{id}/editar")
  public ModelAndView edit(@PathVariable Long id) {
    var modelAndView = new ModelAndView("admin/service/form");

    var service = repository.getReferenceById(id);
    var form = mapper.toForm(service);

    modelAndView.addObject("form", form);

    return modelAndView;
  }

  @PostMapping("/{id}/editar")
  public String edit(@PathVariable Long id, @Valid @ModelAttribute("form") ServiceForm form, BindingResult result) {
    if (result.hasErrors()) {
      return "admin/service/form";
    }

    var service = mapper.toModel(form);
    service.setId(id);
    repository.save(service);

    return "redirect:/admin/services";
  }

  @GetMapping("/{id}/delete")
  public String excluir(@PathVariable Long id) {
    repository.deleteById(id);

    return "redirect:/admin/services";
  }
}
