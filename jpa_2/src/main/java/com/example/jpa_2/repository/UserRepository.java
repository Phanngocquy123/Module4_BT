package com.example.jpa_2.repository;

import com.example.jpa_2.entity.UserEntity;
import com.example.jpa_2.entity.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Page<UserEntity> findByEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(String email, String phone, Pageable pageable);
}
