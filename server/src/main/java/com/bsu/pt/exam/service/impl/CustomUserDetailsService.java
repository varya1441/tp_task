package com.bsu.pt.exam.service.impl;
//
//import net.capparis.health.auth.client.UserServiceFeignClient;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
////@Service
////public class CustomUserDetailsService implements UserDetailsService {
////
////    private UserServiceFeignClient userServiceFeignClient;
////
////    public CustomUserDetailsService(UserServiceFeignClient userServiceFeignClient) {
////        this.userServiceFeignClient = userServiceFeignClient;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return userServiceFeignClient.getByLogin(username).get();
////    }
////}
