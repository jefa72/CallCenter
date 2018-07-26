package com.jorge_forero_software.callcenter;

import org.junit.*;

import static org.junit.Assert.assertEquals;


public class DispatcherTest {


    @Test
    public void dispatchCallTest() throws InterruptedException {

        Dispatcher dispatcher = new Dispatcher(7, 2, 1);

        for(int i = 1; i <= 10; i++) {

            //Asserts the last employee in the hierarchy is who must attend the call 10
            if (i == 10){
                assertEquals(dispatcher.dispatchCall(), "Empleado director10 attended a call. ");
            }else {
                dispatcher.dispatchCall();//dispatches a calls
            }
        }
        dispatcher.cleanResources();
    }
}
