package com.spring.crudapi.crudapi.service.impl;

import com.spring.crudapi.crudapi.model.ApiResponse;
import com.spring.crudapi.crudapi.model.Fruit;
import com.spring.crudapi.crudapi.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public ApiResponse addFruit(Fruit fruit) {
        log.info("Reached FruitServiceImpl.addFruit()");
        log.info("Number of Fruits Found: "+ fruits.size());
        ApiResponse apiResponse = new ApiResponse();
        if(StringUtils.isNotBlank(fruit.getName()) && fruit.getId()>0 && fruit.getWeight()>0){
            fruits.add(fruit);
            apiResponse.setResponseCode(201);
            apiResponse.setData(fruit);
            apiResponse.setMessage("Fruit Added");
            log.info("Added: "+ fruit.toString());
            return apiResponse;
        }
        log.info("Invalid Data - id, name or weight missing/non-zero in payload");
        apiResponse.setResponseCode(400);
        apiResponse.setMessage("Invalid data");
        return apiResponse;
    }

    @Override
    public ApiResponse updateFruit(Fruit fruit) {
        ApiResponse apiResponse = new ApiResponse();
        if(fruit.getId()<=0 && fruit.getWeight()<=0 && StringUtils.isBlank(fruit.getName())){
            log.info("Invalid Data - id, name or weight missing/non-zero in payload");
            return new ApiResponse(400, "Fruit id invalid: "+ fruit.getId(), null);
        }
        for (Fruit item: fruits) {
            if (fruit.getId() == item.getId()) {
                log.info("Fruit found for id: " + item.getId());
                log.warn("Attempting update now");
                if(StringUtils.isNotBlank(fruit.getName()) && fruit.getId()>0 && fruit.getWeight()>0){
                    fruits.remove(item);    //removing fruit
                    fruits.add(fruit);      //adding fruit
                    apiResponse.setResponseCode(200);
                    apiResponse.setData(fruit);
                    apiResponse.setMessage("Fruit Updated");
                    log.info("Updated: "+ fruit.toString());
                    return apiResponse;
                } else {
                    log.info("Invalid Data - id, name or weight missing/non-zero in payload");
                    apiResponse.setResponseCode(400);
                    apiResponse.setMessage("Invalid data");
                    return apiResponse;
                }
            }
        }
        return new ApiResponse(404, "Fruit not found for id: "+ fruit.getId(), null);
    }

    @Override
    public ApiResponse removeFruitById(int id) {
        ApiResponse apiResponse = new ApiResponse();
        if(id<=0){
            log.info("Invalid Data - id, name or weight missing/non-zero in payload");
            return new ApiResponse(400, "Fruit id invalid: "+ id, null);
        }
        for (Fruit fruit: fruits) {
            if (id == fruit.getId()) {
                log.info("Fruit found for id: " + fruit.getId());
                log.warn("Attempting removal now");
                if(StringUtils.isNotBlank(fruit.getName()) && fruit.getId()>0 && fruit.getWeight()>0){
                    fruits.remove(fruit);    //removing fruit

                    apiResponse.setResponseCode(200);
                    apiResponse.setData(null);
                    apiResponse.setMessage("Fruit Removed");
                    log.info("Removed: "+ fruit.toString());
                    return apiResponse;
                }
            }
        }
        return new ApiResponse(404, "Fruit not found for id: "+ id, null);
    }


}
