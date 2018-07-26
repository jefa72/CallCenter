package com.jorge_forero_software.callcenter;

public class Empleado {

    private static int id = 1;
    private String name;

    public Empleado(String name) {
        id++;
        this.name = name;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
