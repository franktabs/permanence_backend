package com.example.demo.annotations;

import com.example.demo.validations.UniqueValidatorParameter;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidatorParameter.class)
public @interface UniqueParameter {
    String message() default "Valeur doit être unique";
    String table();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}