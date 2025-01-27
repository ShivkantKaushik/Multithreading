package org.example;

public class SimpleThread extends Thread{


    @Override
    public void run() {
        System.out.println("Inside Run " + Thread.currentThread().getName() + System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {

        //main is also a thread
        System.out.println(Thread.currentThread().getName()); // main

        //first way of starting new thread
        SimpleThread simpleThread = new SimpleThread();

        simpleThread.start();

        //second way is with runnable

        Runnable runnable = () -> {
            System.out.println("Inside Runnable - " + Thread.currentThread().getName() + System.currentTimeMillis());
        };

        //it means code from here on, code in next lines will execute after simpleThread return successfully
        //otherwise sometimes thread1 runs first, sometimes simpleThread runs first, but with join method
        //simpleThread always runs first
        simpleThread.join();

        Thread thread1 = new Thread(runnable);

        thread1.start();


    }

}
