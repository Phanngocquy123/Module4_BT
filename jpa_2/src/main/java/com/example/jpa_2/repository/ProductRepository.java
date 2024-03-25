package com.example.jpa_2.repository;

import com.example.jpa_2.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findFirstByProductNameContains(String name);
    Page<ProductEntity> findByProductNameContains(String productName, Pageable pageable);

}
