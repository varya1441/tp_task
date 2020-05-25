package com.bsu.pt.exam.service.impl;


import com.bsu.pt.exam.dto.LoginRequest;
import com.bsu.pt.exam.dto.RegisterRequest;
import com.bsu.pt.exam.entity.Group;
import com.bsu.pt.exam.entity.JwtToken;
import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.security.JwtTokenProvider;
import com.bsu.pt.exam.service.AuthenticationService;
import com.bsu.pt.exam.service.StudentService;
import com.bsu.pt.exam.service.TokenStore;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserDetailsService userDetailsService;
    private TokenStore tokenStore;
    private StudentService studentService;

    @Autowired
    public AuthenticationServiceImpl(UserDetailServiceImpl userDetailsService,
                                     AuthenticationManager authenticationManager,
                                     JwtTokenProvider jwtTokenProvider,
                                     StudentServiceImpl userServiceImpl,
                                     TokenStoreImpl tokenStore) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = jwtTokenProvider;
        this.studentService = userServiceImpl;
        this.tokenStore = tokenStore;
    }

    @Override
    public Student registration(RegisterRequest registerRequestModel) {

        Student student = createStudent(registerRequestModel);
        Group group = new Group(registerRequestModel.getGroupName());
        group.setStudents(new ArrayList<>(Collections.singletonList(student)));
        return studentService.save(student);
    }

    private Student createStudent(RegisterRequest registerRequestModel) {
        Student newUser = new Student();
        newUser.setLogin(registerRequestModel.getLogin());
        newUser.setPassword(registerRequestModel.getPassword());
        newUser.setRole(registerRequestModel.getRole());
        newUser.setName(registerRequestModel.getName());
        return newUser;
    }

    @Override
    public JwtToken login(LoginRequest loginRequest) throws AuthenticationException, ExpiredJwtException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        final String refreshToken = tokenProvider.generateRefreshToken((User) authentication);
        JwtToken jwtToken = new JwtToken(token, refreshToken);
        tokenStore.storeToken(jwtToken);
        return jwtToken;
    }

    @Override
    public JwtToken refresh(String refreshToken) throws AuthenticationException, ExpiredJwtException {
        String username = tokenProvider.getUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenProvider.validateToken(refreshToken, userDetails)) {
            final Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            final String newRefreshToken = tokenProvider.generateRefreshToken((User) authentication);
            return new JwtToken(token, newRefreshToken);
        }
        return null;
    }

    @Override
    public void logout(String accessToken) {
        tokenStore.removeToken(accessToken);
    }
}
