package com.bsu.pt.exam.security;

import com.bsu.pt.exam.service.impl.TokenStoreImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60;
    public static final long REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60;
    public static final String SIGNING_KEY = "this is a signing key :)";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    TokenStoreImpl tokenStore;

    @Autowired
    public JwtTokenProvider(TokenStoreImpl tokenStore) {
        this.tokenStore = tokenStore;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        tokenStore.checkToken(token);
        final String userName = getUsernameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
