package com.bsu.pt.exam.service;


import com.bsu.pt.exam.entity.JwtToken;

public interface TokenStore {

    JwtToken storeToken(JwtToken token);

    void removeToken(String accessToken);

    JwtToken checkToken(String accessToken);

}
