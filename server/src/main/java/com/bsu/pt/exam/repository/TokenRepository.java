package com.bsu.pt.exam.repository;


import com.bsu.pt.exam.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<JwtToken, String> {
}
