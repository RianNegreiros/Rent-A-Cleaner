package com.github.riannegreiros.ExpressCleaning.core.services.email.adapters;

import com.github.riannegreiros.ExpressCleaning.core.services.email.dtos.EmailParams;

public interface EmailService {

    void sendEmailWithTemplateHtml(EmailParams params);

}