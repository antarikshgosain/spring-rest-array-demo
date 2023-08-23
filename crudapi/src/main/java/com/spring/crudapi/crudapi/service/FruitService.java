package com.spring.crudapi.crudapi.service;

import com.spring.crudapi.crudapi.model.ApiResponse;
import com.spring.crudapi.crudapi.model.Fruit;

public interface FruitService {

    public ApiResponse getFruitById(int id);

    public ApiResponse getFruits();

    ApiResponse addFruit(Fruit fruit);

    ApiResponse updateFruit(Fruit fruit);

    ApiResponse removeFruitById(int id);
}
