# Simple Spring-boot App
Springboot app that has CRUD functionality on an Array List of object `Fruit` 

Each `Fruit` object in the Array List consists of the following 
1. `id` (integer like 1, 2, 3)
2. `name` (string like 'apple', 'banana', 'watermelon')
3. `weight` (integer like 5, 10, 20)

## Controller
Base path - `http://localhost:8800`
1. GET `/hello`: responds with Hello to indicate that the server and app have started properly
2. GET `/fruits`: list of all fruits (3 fruits will be added by default when the application loads)
3. GET `/fruit/{id}`: returns fruit object based on given id
4. POST `/fruit`: adds the given fruit to the fruit's list (payload is mandatory)
5. PUT `/fruit/{id}`: updates the given based on id (payload is mandatory)
6. DELETE `/fruit/{id}`: deletes the fruit from the list based on the id
7. HEALTH - `/health`: responds with "Health - OK" and HTTP status code of 200 (OK) 
8. SERVER-INFO - `/server-info`: gives active port number, Device IP address and Device MAC Address
9. HARDWARE-INFO - `/hardware-info`: gives used & total RAM (with %), used and total Disk Space (with %) and CPU Utilization %
10. EMPTY ENDPOINT - `/`: gives back the URL to Swagger Documentation for User to view all of the available Endpoints

## Application Configurations
The Default server port is set to `8800`
https://github.com/antarikshgosain/spring-rest-array-demo/blob/24e4963c32ed84341fb3d17aa01fcd91c8240625/crudapi/src/main/resources/application.properties#L3

## Postman Collection
Simply download and import the following Collection file in your Postman and it is good to go
![image](https://github.com/antarikshgosain/spring-rest-array-demo/assets/15723458/8d612031-283a-4db9-bb79-7045b922bfde)


## Swagger URL and Configurations

* Swagger UI URL: http://localhost:8800/swagger-ui/#/
![image](https://github.com/antarikshgosain/spring-rest-array-demo/assets/15723458/a88d3626-e30d-4669-b637-ac215780a518)

* Swagger base package defined in 
https://github.com/antarikshgosain/spring-rest-array-demo/blob/24e4963c32ed84341fb3d17aa01fcd91c8240625/crudapi/src/main/java/com/spring/crudapi/crudapi/config/swagger/SpringFoxConfig.java#L16
* Swagger Configuration
https://github.com/antarikshgosain/spring-rest-array-demo/blob/24e4963c32ed84341fb3d17aa01fcd91c8240625/crudapi/src/main/resources/application.properties#L6
