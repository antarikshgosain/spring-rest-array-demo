package com.spring.crudapi.crudapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fruit {

    int id;
    String name;
    int weight;

}
