package com.devcamp.eztour.domain.reserv;

import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        ValidationUtils.rejectIfEmpty(errors, "usr_nm", "required");
        ValidationUtils.rejectIfEmpty(errors, "email", "required");
    }
}
