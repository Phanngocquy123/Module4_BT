package com.ra.springmvc2.service;

import com.ra.springmvc2.model.entity.Computer;

import java.util.List;

public interface ComputerService {
    List<Computer> findAll();
}
