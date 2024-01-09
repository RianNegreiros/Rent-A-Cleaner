package com.github.riannegreiros.ExpressCleaning.core.services.email.dtos;

import java.util.Map;

public class EmailParams {
    private String destination;
    private String subject;
    private String template;
    private Map<String, Object> props;

    public EmailParams() {
    }

    public EmailParams(String destination, String subject, String template, Map<String, Object> props) {
        this.destination = destination;
        this.subject = subject;
        this.template = template;
        this.props = props;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public static class Builder {
        private String destination;
        private String subject;
        private String template;
        private Map<String, Object> props;

        public Builder(String destination, String subject, String template) {
            this.destination = destination;
            this.subject = subject;
            this.template = template;
        }

        public Builder setProps(Map<String, Object> props) {
            this.props = props;
            return this;
        }

        public EmailParams build() {
            EmailParams emailParams = new EmailParams();
            emailParams.destination = this.destination;
            emailParams.subject = this.subject;
            emailParams.template = this.template;
            emailParams.props = this.props;
            return emailParams;
        }
    }

}
