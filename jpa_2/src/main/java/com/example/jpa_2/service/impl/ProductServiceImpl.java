package com.example.jpa_2.service.impl;

import com.example.jpa_2.entity.ProductEntity;
import com.example.jpa_2.repository.ProductRepository;
import com.example.jpa_2.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity> findByName(String name, int page, int pageSize, String sortBy, String direction) {
        ProductEntity entity = productRepository.findFirstByProductNameContains("2");

        Sort sort = Sort.by(direction.equalsIgnoreCase("asc")? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<ProductEntity> data = productRepository.findByProductNameContains(name, pageable);
        return data;
    }
}
