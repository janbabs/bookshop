package com.janbabs.bookshop.adnotation;

import com.janbabs.bookshop.transport.UserTO;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingLoginValidator.class)
public @interface ExistingLogin {
    String message() default "{Nazwa użytkownika jest zajęta}";
    Class<?>[] groups() default {};
    Class<? extends UserTO>[] payload() default {};
}
