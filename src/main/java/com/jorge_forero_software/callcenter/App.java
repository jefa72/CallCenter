package com.jorge_forero_software.callcenter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws InterruptedException{

        Dispatcher myDispatcher = new Dispatcher(7, 2, 1);
        for(int i = 0; i < 15; i++){
            System.out.println(myDispatcher.dispatchCall());
        }
        Thread.sleep(100000);
        for(int i = 0; i < 15; i++){
            System.out.println(myDispatcher.dispatchCall());
        }
        myDispatcher.cleanResources();
    }
}
