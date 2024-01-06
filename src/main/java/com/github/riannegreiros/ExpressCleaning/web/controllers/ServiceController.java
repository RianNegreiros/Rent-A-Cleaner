package com.github.riannegreiros.ExpressCleaning.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.riannegreiros.ExpressCleaning.web.dtos.FlashMessage;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ServiceForm;
import com.github.riannegreiros.ExpressCleaning.web.services.ServiceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/services")
public class ServiceController {

  @Autowired
  private ServiceService service;

  @GetMapping
  public ModelAndView getAll() {
    var modelAndView = new ModelAndView("admin/service/list");

    modelAndView.addObject("services", service.getAll());

    return modelAndView;
  }

  @GetMapping("/register")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/service/form");

    modelAndView.addObject("form", new ServiceForm());

    return modelAndView;
  }

  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("form") ServiceForm form, BindingResult result,
      RedirectAttributes attrs) {
    if (result.hasErrors()) {
      return "admin/service/form";
    }

    service.register(form);
    attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service successfully registered!"));

    return "redirect:/admin/services";
  }

  @GetMapping("/{id}/edit")
  public ModelAndView edit(@PathVariable Long id) {
    var modelAndView = new ModelAndView("admin/service/form");

    modelAndView.addObject("form", service.getById(id));

    return modelAndView;
  }

  @PostMapping("/{id}/edit")
  public String edit(@PathVariable Long id, @Valid @ModelAttribute("form") ServiceForm form, BindingResult result,
      RedirectAttributes attrs) {
    if (result.hasErrors()) {
      return "admin/service/form";
    }

    service.edit(form, id);
    attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service successfully edited!"));

    return "redirect:/admin/services";
  }

  @GetMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes attrs) {
    service.deleteById(id);
    attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service successfully deleted!"));

    return "redirect:/admin/services";
  }
}