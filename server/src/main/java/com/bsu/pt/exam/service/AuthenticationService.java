package com.bsu.pt.exam.service;

import com.bsu.pt.exam.dto.LoginRequest;
import com.bsu.pt.exam.dto.RegisterRequest;
import com.bsu.pt.exam.entity.JwtToken;
import com.bsu.pt.exam.entity.Student;
import io.jsonwebtoken.ExpiredJwtException;


public interface AuthenticationService {
	Student registration(RegisterRequest registerRequest);
	JwtToken login(LoginRequest loginRequest);
	JwtToken refresh(String refreshToken) throws ExpiredJwtException;
	void logout(String accessToken);
}
