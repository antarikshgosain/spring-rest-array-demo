package com.spring.crudapi.crudapi.service;

import com.spring.crudapi.crudapi.model.ApiResponse;

public interface FruitService {

    public ApiResponse getFruitById(int id);

    public ApiResponse getFruits();
}
