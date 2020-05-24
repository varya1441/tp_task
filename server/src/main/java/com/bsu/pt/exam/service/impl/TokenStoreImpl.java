package com.bsu.pt.exam.service.impl;


import com.bsu.pt.exam.entity.JwtToken;
import com.bsu.pt.exam.exception.StudentValidationException;
import com.bsu.pt.exam.repository.TokenRepository;
import com.bsu.pt.exam.service.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreImpl implements TokenStore {

	private TokenRepository tokenRepository;

	@Autowired
	public TokenStoreImpl(TokenRepository tokenRepository)  {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public JwtToken storeToken(JwtToken token) {
		return tokenRepository.save(token);
	}

	@Override
	public void removeToken(String accessToken) {
		tokenRepository.deleteById(accessToken);
	}

	@Override
	public JwtToken checkToken(String accessToken) {
		try { 
			return tokenRepository.findById(accessToken).get();
		} catch (Exception e) {
			throw new StudentValidationException("Token has been invalid");
		}
	}

}
