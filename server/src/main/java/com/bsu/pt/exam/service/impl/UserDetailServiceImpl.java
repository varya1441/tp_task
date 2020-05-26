package com.bsu.pt.exam.service.impl;


import com.bsu.pt.exam.entity.Student;
import com.bsu.pt.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private StudentService studentService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return createUserDetails(studentService.findByLogin(s));
    }

    private UserDetails createUserDetails(Student user) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Student user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return authorities;
    }

}
