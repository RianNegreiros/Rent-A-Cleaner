package com.github.riannegreiros.ExpressCleaning.core.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HourLaterThanValidator.class)
public @interface HourLaterThan {
    String message() default "the time cannot be less than {StartTime}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int startTime() default 0;

    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        HourLaterThan[] value();
    }
}
