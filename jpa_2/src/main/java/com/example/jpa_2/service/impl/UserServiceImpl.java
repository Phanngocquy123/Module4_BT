package com.example.jpa_2.service.impl;

import com.example.jpa_2.entity.UserEntity;
import com.example.jpa_2.repository.UserRepository;
import com.example.jpa_2.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findId(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public UserEntity add(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(UserEntity entity) {
        userRepository.delete(entity);
    }

    @Override
    public Page<UserEntity> searchAndPagination(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("email").ascending());
        return userRepository.findByEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(key, key, pageable);
    }
}
