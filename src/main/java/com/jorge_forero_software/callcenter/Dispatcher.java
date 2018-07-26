package com.jorge_forero_software.callcenter;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Dispatcher {

    private int operariosNum, supervisoresNum, directoresNum;
    private Queue<Empleado> operariosStaff;
    private Queue<Empleado> supervisoresStaff;
    private Queue<Empleado> directoresStaff;

    //Threads poll to atend calls
    private ExecutorService attendanceCallService;
    private int counter = 0;

    public Dispatcher(int o, int s, int d){
        this.operariosNum = o;
        this.supervisoresNum = s;
        this.directoresNum = d;
        createModelHelper();
        attendanceCallService = Executors.newFixedThreadPool(10);//maximum 10 call can be attended
    }

    private void createModelHelper(){
        operariosStaff = new ArrayDeque<>(this.operariosNum);
        supervisoresStaff = new ArrayDeque<>(this.supervisoresNum);
        directoresStaff = new ArrayDeque<>(this.supervisoresNum);

        Empleado emp;
        for(int i = 0; i < this.operariosNum; i++){
            emp = new Operador("operador" + Empleado.getId());
            operariosStaff.add(emp);
        }
        for(int i = 0; i < this.supervisoresNum; i++){
            emp = new Supervisor("supervisor" + Empleado.getId());
            supervisoresStaff.add(emp);
        }
        for(int i = 0; i < this.directoresNum; i++){
            emp = new Director("director" + Empleado.getId());
            directoresStaff.add(emp);
        }
    }

    public void cleanResources(){
        attendanceCallService.shutdown();
    }

    public String dispatchCall()throws InterruptedException {

        Empleado e;
        String msg;

        if(counter < 10){
            counter++;
            e = getNextAvailableE();
                attendanceCallService.submit(() -> {
                    try {
                        this.attendCall(e);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
            msg = String.format("Empleado %s attended a call. ", e.getName());
        }else{
            msg = "Sorry. At this time nobody can attend your call. Please, call later";
        }
        return msg;
    }

    private void attendCall(Empleado e)throws InterruptedException {

        long callTime = 5000*(1 + (long)(Math.random()));
        long startTime = System.currentTimeMillis();

        while((System.currentTimeMillis() - startTime) < callTime) {
            Thread.sleep(5000);
        }
        System.out.println("Call finished!");
        counter--;

        //Reasigna el empleado que atendió la llamada al pool de empleados libres
        if(e instanceof Operador) {
            operariosStaff.add(e);
            System.out.println("Operador libre");
        }else if(e instanceof Supervisor) {
            supervisoresStaff.add(e);
            System.out.println("Supervisor libre");
        }else{
            directoresStaff.add(e);
            System.out.println("Director libre");
        }
    }

    private Empleado getNextAvailableE() {

        Empleado emp = null;

        //Identifica en orden gerárquico el siguiente empleado libre
        if(operariosStaff.size() > 0){
            emp = (Operador)operariosStaff.remove();
        }else if(supervisoresStaff.size() > 0){
            emp = (Supervisor)supervisoresStaff.remove();
        }else{
            emp = (Director)directoresStaff.remove();
        }
        return emp;
    }
}
