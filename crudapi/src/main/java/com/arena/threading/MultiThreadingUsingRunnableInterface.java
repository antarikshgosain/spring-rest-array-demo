package com.arena.threading;

class MyRunnable implements Runnable {

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

public class MultiThreadingUsingRunnableInterface {
    public static void main(String[] args) {
        MyRunnable runnable1 = new MyRunnable();
        Thread t1 = new Thread(runnable1);
        t1.start();
    }
}
