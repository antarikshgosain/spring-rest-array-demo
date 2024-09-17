package com.arena.streams;

import com.spring.crudapi.crudapi.model.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit(1, "Apple", 10));
        fruits.add(new Fruit(2, "Banana", 8));
        fruits.add(new Fruit(3, "Cherry", 4));
        fruits.add(new Fruit(4, "Water Melon", 20));
        fruits.add(new Fruit(2, "Black Berries", 3));

        /*System.out.println(fruits.toString());
        //removing all fruits with weight > 9
        List<Fruit> filteredFruits = new ArrayList<>();
        for(Fruit fruit : fruits){
            if(fruit.getWeight() > 9){
                filteredFruits.add(fruit);
            }
        }
        System.out.println(filteredFruits);*/

        //Predicate is a Functional Interface
        Predicate<Fruit> fruitsPredicate = fruit -> {
            if(fruit.getName().startsWith("A") || fruit.getName().startsWith("B")) {
                return true;
            }
            return false;
        };

        System.out.println("Filtered Fruits (A||B)");
        System.out.println(fruits.stream().filter(fruitsPredicate).collect(Collectors.toList()));


        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * 2)
                .sum();
        System.out.println("Sum of even numbers multiplied by 2: " + sum);



        long endTime = System.currentTimeMillis();
        System.out.println("==========REPORT==========");
        System.out.println("Start Time: " + startTime );
        System.out.println(" End  Time: " + endTime );
        System.out.println("Time Taken: " + (endTime-startTime) );
        System.out.println("===========END============");
    }

}
