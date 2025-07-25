package com.josias.gestorcondominio.model;

public class Morador extends Pessoa {
    
    private Residencia residencia;

    public Morador(){}
    
    public Morador(Residencia residencia, int idade, String nome, String rg, String cpf) {
        super(idade, nome, rg, cpf);
        this.residencia = residencia;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
    }
    
}
