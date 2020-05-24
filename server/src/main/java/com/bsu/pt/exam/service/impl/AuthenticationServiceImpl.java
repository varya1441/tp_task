package com.bsu.pt.exam.service.impl;



import com.bsu.pt.exam.client.UserServiceFeignClient;
import com.bsu.pt.exam.controller.AuthController;
import com.bsu.pt.exam.dto.LoginRequest;
import com.bsu.pt.exam.dto.RegisterRequest;
import com.bsu.pt.exam.entity.JwtToken;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.security.JwtTokenProvider;
import com.bsu.pt.exam.service.AuthenticationService;
import com.bsu.pt.exam.service.StudentService;
import com.bsu.pt.exam.service.TokenStore;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);

	private JwtTokenProvider tokenProvider;
	private TokenStore tokenStore;
	private StudentService studentService;

	@Autowired
	public AuthenticationServiceImpl(
			JwtTokenProvider tokenProvider,
			TokenStore tokenStore,
			StudentService studentService) {
		this.tokenProvider = tokenProvider;
		this.tokenStore = tokenStore;
		this.studentService = studentService;
	}

	@Override
	public Student registration(RegisterRequest registerRequestModel) {
		Student result = new Student(registerRequestModel.getLogin(), registerRequestModel.getPassword());
		return studentService.save(result);
	}

	@Override
	public JwtToken login(LoginRequest loginRequest) {
		Optional<Student> user = studentService.getByLogin(loginRequest.getLogin());
		if (user.isPresent()) {
			final String token = tokenProvider.generateToken(user.get());
			final String refreshToken = tokenProvider.generateRefreshToken(user.get());
			return tokenStore.storeToken(new JwtToken(token, refreshToken));
		} else {
			logger.error("Cloud not find user");
			throw new UserValidationException("Cloud not find user");
		}
	}

	@Override
	public JwtToken refresh(String refreshToken) throws ExpiredJwtException {
		String username = tokenProvider.getUsernameFromToken(refreshToken);
		Student userDetails = studentService.getByLogin(username).get();
		if ( tokenProvider.validateToken(refreshToken, userDetails) ) {
			final String token = tokenProvider.generateToken(userDetails);
			final String newRefreshToken = tokenProvider.generateRefreshToken(userDetails);
			return new JwtToken(token, newRefreshToken);
		}
		return null;
	}

	@Override
	public void logout(String accessToken) {
		tokenStore.removeToken(accessToken);
	}
}
