package com.bsu.pt.exam.controller;


import com.bsu.pt.exam.dto.LoginRequest;
import com.bsu.pt.exam.dto.RegisterRequest;
import com.bsu.pt.exam.entity.JwtToken;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.exception.StudentValidationException;
import com.bsu.pt.exam.service.AuthenticationService;
import com.bsu.pt.exam.service.impl.AuthenticationServiceImpl;
import com.bsu.pt.exam.validators.LoginUserValidator;
import com.bsu.pt.exam.validators.RegistrationUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private AuthenticationService authService;
	private RegistrationUserValidator regUserValidator;
	private LoginUserValidator loginUserValidator;

	@Autowired
	public AuthController(AuthenticationServiceImpl authService,
						  RegistrationUserValidator regUserValidator,
						  LoginUserValidator loginUserValidator) {
		this.authService = authService;
		this.regUserValidator = regUserValidator;
		this.loginUserValidator = loginUserValidator;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<Student> signUp(@RequestBody RegisterRequest rRequest, BindingResult bindingResult) {
		logger.warn("post /auth/register");
		regUserValidator.validate(rRequest, bindingResult);
		logger.warn("validated");
		if (bindingResult.hasErrors()) {
			logger.warn("validation error");
			throw new StudentValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
		}
		return ResponseEntity.ok(authService.registration(rRequest));
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> signIn(@RequestBody LoginRequest lRequest, BindingResult bindingResult) {
		logger.warn("post /auth/login");
		loginUserValidator.validate(lRequest, bindingResult);
		logger.warn("validated");
		if (bindingResult.hasErrors()) {
			logger.warn("validation error");
			throw new StudentValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
		}
		return ResponseEntity.ok(authService.login(lRequest));
	}

	@PostMapping(value = "/refresh-token")
	public ResponseEntity<JwtToken> refresh(@RequestBody String refreshToken) {
		return ResponseEntity.ok(authService.refresh(refreshToken));
	}

	@PostMapping(value = "/logout")
	public void logout(@RequestBody JwtToken accessToken) {
		authService.logout(accessToken.getAccessToken());
	}

}
