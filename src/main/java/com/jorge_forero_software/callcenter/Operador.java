package com.jorge_forero_software.callcenter;

public class Operador extends Empleado {

    private int categoria = 1;

    public Operador(String name) {
        super(name);
    }

    public int getCategoria() {
        return categoria;
    }
}
