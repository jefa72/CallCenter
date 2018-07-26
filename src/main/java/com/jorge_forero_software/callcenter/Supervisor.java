package com.jorge_forero_software.callcenter;

public class Supervisor extends Empleado {
    private int categoria = 2;

    public Supervisor(String name) {
        super(name);
    }

    public int getCategoria() {
        return categoria;
    }
}
