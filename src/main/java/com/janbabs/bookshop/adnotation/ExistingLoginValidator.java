package com.janbabs.bookshop.adnotation;

import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.service.UserServices;
import com.janbabs.bookshop.transport.UserTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingLoginValidator implements ConstraintValidator<ExistingLogin, UserTO> {
    @Autowired
    private UserServices userServices;

    public void initialize(ExistingLogin constraint) {

    }

    public boolean isValid(UserTO userTO, ConstraintValidatorContext context) {
        if (userTO.getLogin() != null) {
            User existingUser = userServices.findOne(userTO.getLogin());
            if (existingUser != null) {
                return false;
            }
        }
        return true;
    }
}
