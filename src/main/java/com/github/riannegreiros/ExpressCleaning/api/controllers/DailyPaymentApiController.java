package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PaymentRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.DailyPaymentApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily/{id}")
public class DailyPaymentApiController {
    @Autowired
    private DailyPaymentApiService service;

    @RentACleanerPermissions.isCleanerOrClient
    @PostMapping("/pay")
    public MessageResponse pay(@RequestBody @Valid PaymentRequest request, @PathVariable Long id) {
        return service.pay(request, id);
    }
}
