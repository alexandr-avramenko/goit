package org.example;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Runnable task1 = () -> {
            while(true) {
                System.out.println(System.currentTimeMillis() - start);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        Runnable task2 = () -> {
            while(true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("5 seconds has passed");
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}