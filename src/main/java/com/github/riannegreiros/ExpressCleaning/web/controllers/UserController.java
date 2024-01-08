package com.github.riannegreiros.ExpressCleaning.web.controllers;

import java.security.Principal;

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

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ChangePasswordForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.FlashMessage;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserEditForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserRegisterForm;
import com.github.riannegreiros.ExpressCleaning.web.services.WebUserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserController {

  @Autowired
  private WebUserService service;

  @GetMapping
  public ModelAndView getAll() {
    var modelAndView = new ModelAndView("admin/user/list");

    modelAndView.addObject("users", service.getAll());

    return modelAndView;
  }

  @GetMapping("/register")
  public ModelAndView register() {
    var modelAndView = new ModelAndView("admin/user/register-form");

    modelAndView.addObject("registerForm", new UserRegisterForm());

    return modelAndView;
  }

  @PostMapping("/register")
  public String register(
      @Valid @ModelAttribute("registerForm") UserRegisterForm userRegisterForm,
      BindingResult result,
      RedirectAttributes attrs) {
    if (result.hasErrors()) {
      return "admin/user/register-form";
    }

    try {
      service.register(userRegisterForm);
      attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User successfully registered!"));
    } catch (ValidationException e) {
      result.addError(e.getFieldError());
      return "admin/user/register-form";
    }

    return "redirect:/admin/users";
  }

  @GetMapping("/{id}/edit")
  public ModelAndView edit(@PathVariable Long id) {
    var modelAndView = new ModelAndView("admin/user/edit-form");

    modelAndView.addObject("editForm", service.getFormById(id));

    return modelAndView;
  }

  @PostMapping("/{id}/edit")
  public String edit(
      @PathVariable Long id,
      @Valid @ModelAttribute("editForm") UserEditForm editForm,
      BindingResult result,
      RedirectAttributes attrs) {
    if (result.hasErrors()) {
      return "admin/user/edit-form";
    }

    try {
      service.edit(editForm, id);
      attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User edited successfully!"));
    } catch (ValidationException e) {
      result.addError(e.getFieldError());
      return "admin/user/edit-form";
    }

    return "redirect:/admin/users";
  }

  @GetMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes attrs) {
    service.deleteById(id);
    attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User successfully deleted!"));

    return "redirect:/admin/users";
  }

  @GetMapping("/change-password")
  public ModelAndView changePassword() {
    var modelAndView = new ModelAndView("admin/user/change-password");

    modelAndView.addObject("changePasswordForm", new ChangePasswordForm());

    return modelAndView;
  }

  @PostMapping("/change-password")
  public String changePassword(
      @Valid @ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
      BindingResult result,
      RedirectAttributes atts,
      Principal principal) {
    if (result.hasErrors()) {
      return "admin/user/change-password";
    }

    try {
      service.changePassword(changePasswordForm, principal.getName());
      atts.addFlashAttribute("alert", new FlashMessage("alert-success", "Password changed successfully!"));
    } catch (ValidationException e) {
      result.addError(e.getFieldError());
      return "admin/user/change-password";
    }

    return "redirect:/admin/users/change-password";
  }
}
