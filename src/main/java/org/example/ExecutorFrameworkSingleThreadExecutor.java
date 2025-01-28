package org.example;

import java.util.concurrent.*;

public class ExecutorFramework {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = () -> {
            System.out.println("Inside runnable - " + Thread.currentThread().getName());
        };

        ExecutorService es = Executors.newSingleThreadExecutor();

        es.execute(runnable);

        System.out.println("Is Shutdown " + es.isShutdown());

        System.out.println("Is Terminated " + es.isTerminated());

        //************Callable****************

        Future<Integer> priceFromClass = es.submit(new PriceCalculation(2));
        System.out.println("Between Future and actual print");

        //We can see that this get method blocks the execution until the task is complete by adding sleep in callable.
        System.out.println("Price from class " + priceFromClass.get());
        System.out.println("After actual print");


        Callable<Integer> callableLambda = () -> {
            System.out.println("Inside callable, lambda");
            return 10;
        };

        Future<Integer> priceFromLambda = es.submit(callableLambda);

        System.out.println("Price from lambda " + priceFromLambda.get());


        //if we do not do shutdown, this program will keep running, means at top no option run this class
        // as it is already running
        es.shutdown();



    }

}


class PriceCalculation implements Callable<Integer> {

    private final Integer price;

    PriceCalculation(Integer price){
        this.price = price;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("Inside Callable,Class");
        Thread.sleep(2000);
        return price + 10;
    }
}
