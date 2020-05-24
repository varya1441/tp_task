package com.bsu.pt.exam.validators;


import com.bsu.pt.exam.dto.LoginRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class LoginUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return LoginRequest.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
	}
}
