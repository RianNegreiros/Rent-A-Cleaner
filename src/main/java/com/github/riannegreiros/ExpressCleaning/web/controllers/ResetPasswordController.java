package com.github.riannegreiros.ExpressCleaning.web.controllers;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.web.dtos.FlashMessage;
import com.github.riannegreiros.ExpressCleaning.web.dtos.PasswordResetConfirmationForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.PasswordResetForm;
import com.github.riannegreiros.ExpressCleaning.web.services.WebPasswordResetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/reset-password")
public class ResetPasswordController {

    @Autowired
    private WebPasswordResetService service;

    @GetMapping
    public ModelAndView resetPassword() {
        var modelAndView = new ModelAndView("admin/auth/reset-password");

        modelAndView.addObject("form", new PasswordResetForm());

        return modelAndView;
    }

    @PostMapping
    public String resetPassword(@ModelAttribute PasswordResetForm form, RedirectAttributes attrs) {
        service.requestPasswordReset(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Verifique o seu e-mail para ter acesso ao link de reset de senha"));
        return "redirect:/admin/reset-password";
    }

    @GetMapping("/confirmation")
    public ModelAndView resetPasswordConfirmation() {
        var modelAndView = new ModelAndView("admin/auth/reset-password-confirmation");
        modelAndView.addObject("form", new PasswordResetConfirmationForm());
        return modelAndView;
    }

    @PostMapping("/confirmation")
    public String resetPasswordConfirmation(
            @ModelAttribute("form") @Valid PasswordResetConfirmationForm form,
            BindingResult result,
            @RequestParam String token
    ) {
        try {
            service.confirmPasswordReset(token, form);
        } catch (ValidationException e) {
            result.addError(e.getFieldError());
            return "admin/auth/reset-password-confirmation";
        }
        return "redirect:/admin/login";
    }
}
