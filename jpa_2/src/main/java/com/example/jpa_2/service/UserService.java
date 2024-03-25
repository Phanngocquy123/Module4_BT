package com.example.jpa_2.service;

import com.example.jpa_2.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Predicate;

public interface UserService {
    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findId(String id);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    UserEntity add(UserEntity entity);
    UserEntity update(UserEntity entity);
    void delete(UserEntity entity);
    Page<UserEntity> searchAndPagination(String key, int page, int size);
}
