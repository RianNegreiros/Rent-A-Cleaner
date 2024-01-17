package com.github.riannegreiros.ExpressCleaning.web.controllers;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.web.services.WebDailyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/daily")
public class DailyController {
    @Autowired
    private WebDailyService service;

    @GetMapping
    public ModelAndView getDaily(
            @RequestParam(required = false) List<DailyStatus> status,
            @RequestParam(required = false, defaultValue = "") String client
    ) {
        var modelAndView = new ModelAndView("admin/daily/list");

        modelAndView.addObject("daily", service.getDaily(client, status));

        return modelAndView;
    }

    @GetMapping("/{id}/pay")
    public String pay(@PathVariable Long id, HttpServletRequest request) {
        service.pay(id);
        var previousRoute = request.getHeader("Referer");
        return "redirect:" + previousRoute;
    }
}
