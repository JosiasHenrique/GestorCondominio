package com.josias.gestorcondominio.model;

public class Morador extends Pessoa {
    
    private Residencia residencia;

    public Morador(Residencia residencia) {
        this.residencia = residencia;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
    }
    
}
