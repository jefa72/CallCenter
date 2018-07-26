package com.jorge_forero_software.callcenter;

public class Director extends Empleado {

    private int categoria = 3;

    public Director(String name) {
        super(name);
    }

    public int getCategoria() {
        return categoria;
    }
}
