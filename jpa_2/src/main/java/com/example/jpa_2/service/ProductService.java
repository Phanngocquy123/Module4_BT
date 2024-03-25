package com.example.jpa_2.service;

import com.example.jpa_2.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
    Page<ProductEntity> findByName(String name, int page, int pageSize, String sortBy, String direction);

}
