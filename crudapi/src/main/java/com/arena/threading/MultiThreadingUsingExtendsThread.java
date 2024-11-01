package com.arena.threading;

class MyThread extends Thread {
    @Override
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultiThreadingUsingExtendsThread {

    public static void main(String[] args) {
        printClassAndMethodName();
        MyThread t1 = new MyThread();
        t1.start();
    }


    public static void printClassAndMethodName() {
        // Get the stack trace element for the current method call
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // The element at index 2 is the current method (this method)
        String className = stackTrace[2].getClassName();
        String methodName = stackTrace[2].getMethodName();

        System.out.println("Class  Name: " + className);
        System.out.println("Method Name: " + methodName);
    }
}
