package com.arena.threading.deadlocks;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DetectingDeadlocksUsingMXBean {
    public static void main(String[] args) {
        // Create a simple deadlock situation
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lock2) { System.out.println("Thread 1"); }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lock1) { System.out.println("Thread 2"); }
            }
        });

        t1.start();
        t2.start();

        // Detect Deadlock
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();

        if (threadIds != null) {
            System.out.println("Deadlock detected!");
        } else {
            System.out.println("No deadlock.");
        }
    }
}
