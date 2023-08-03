package com.spring.crudapi.crudapi.service.impl;

import com.spring.crudapi.crudapi.model.ApiResponse;
import com.spring.crudapi.crudapi.model.Fruit;
import com.spring.crudapi.crudapi.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FruitServiceImpl implements FruitService {

    private static List<Fruit> fruits ;

    static {
        if (fruits == null) {
            log.info("Preparing Fresh Basket of Fruits...");
            fruits =  new ArrayList<>();
            fruits.add(new Fruit(1, "Apple", 5));
            fruits.add(new Fruit(2, "Banana", 10));
            fruits.add(new Fruit(3, "Cherry", 3));
        }
    }

    public ApiResponse getFruitById(int id){
        log.info("Reached FruitServiceImpl.getFruitById() for id: " + id);
        for (Fruit item: fruits){
            if(id == item.getId()){
                log.info("Fruit found for id: " + id);
                return new ApiResponse(200,"Fruit Found", item);
            }
        }
        log.info("Fruit NOT found for id: " + id);
        return new ApiResponse(404, "Fruit not found for id: "+ id, null);
    }

    @Override
    public ApiResponse getFruits() {
        log.info("Reached FruitServiceImpl.getFruits()");
        log.info("Number of Fruits Found: "+ fruits.size());
        return new ApiResponse(200, "Number of Fruits Found: "+ fruits.size(), fruits);
    }


}
