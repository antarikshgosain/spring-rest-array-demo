package com.spring.crudapi.crudapi.controller;

import com.spring.crudapi.crudapi.model.ApiResponse;
import com.spring.crudapi.crudapi.model.Fruit;
import com.spring.crudapi.crudapi.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FruitController {

    @Autowired
    FruitService fruitService;

    @GetMapping(value = "/hello")
    public String hello() {
        log.info("--> FruitController.hello() reached");
        return "Hello Fruit Lover";
    }

    @GetMapping(value = "/fruit/{id}")
    public ApiResponse getFruitById(@PathVariable("id") int id){
        log.info("--> FruitController.getFruitById() reached");
        return fruitService.getFruitById(id);
    }

    @GetMapping(value = "/fruits")
    public ApiResponse getFruits() {
        log.info("--> FruitController.getFruits() reached");
        return fruitService.getFruits();
    }

    @PostMapping(value = "/fruit")
    public ApiResponse addFruit(@RequestBody Fruit fruit) {
        log.info("--> FruitController.addFruit() reached");
        return fruitService.addFruit(fruit);
    }

    @PutMapping(value = "/fruit")
    public ApiResponse updateFruit(@RequestBody Fruit fruit) {
        log.info("--> FruitController.updateFruit() reached");
        return fruitService.updateFruit(fruit);
    }

    @DeleteMapping(value = "/fruit/{id}")
    public ApiResponse removeFruitById(@PathVariable("id") int id){
        log.info("--> FruitController.removeFruitById() reached");
        return fruitService.removeFruitById(id);
    }



}
