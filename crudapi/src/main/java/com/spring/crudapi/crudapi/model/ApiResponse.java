package com.spring.crudapi.crudapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    int responseCode;
    String message;
    Object data;

}
