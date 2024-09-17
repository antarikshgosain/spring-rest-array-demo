package com.arena.lambda;

@FunctionalInterface
interface MyInterface{
    void show();
}

class MyClass implements MyInterface {

    @Override
    public void show() {
        System.out.println("Hello World");
    }
}

public class LambdaBasic {

    public static void main(String[] args) {

        // 1. Old way of doing things
        MyInterface myInterface1 = new MyClass();
        myInterface1.show();

        // 2. Newer way of creating interface impl on the fly
        MyInterface myInterface2 = new MyInterface() {
            public void show() {
                System.out.println("Hola todo Mundo");
            }
        };
        myInterface2.show();

        // 3. Post-modern way of working with less syntax
        MyInterface myInterface3 = () -> System.out.println("Bonjour tout le monde");
        myInterface3.show();

    }

}
