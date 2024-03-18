package com.ra.springmvc2.service.impl;

import com.ra.springmvc2.model.entity.Computer;
import com.ra.springmvc2.repository.Repository;
import com.ra.springmvc2.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private Repository<Computer> computerRepository;


    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll(Computer.class);
    }
}
