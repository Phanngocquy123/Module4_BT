package com.web.jpa.repository;

import com.web.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    @Override
    List<User> findAll();

    User findByEmailAndPassword(String email, String password);

    // User findById(String id);
    boolean existsByEmail(String mail);
    boolean existsByPhone(String phone);



}
