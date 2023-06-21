package com.devcamp.eztour.domain.pay;

import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PayValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return (clazz.equals(PayViewDto.class) || clazz.equals(UserDto.class));
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "pay_prc", "required");
        ValidationUtils.rejectIfEmpty(errors, "used_mlg", "required");
        ValidationUtils.rejectIfEmpty(errors, "rsvt_no", "required");
        ValidationUtils.rejectIfEmpty(errors, "pay_mthd", "required");
        ValidationUtils.rejectIfEmpty(errors, "prd_dtl_cd", "required");
        ValidationUtils.rejectIfEmpty(errors, "usr_nm", "required");
        ValidationUtils.rejectIfEmpty(errors, "email", "required");
        ValidationUtils.rejectIfEmpty(errors, "pay_ftr_prc", "required");
    }
}
