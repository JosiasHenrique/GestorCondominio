package com.josias.gestorcondominio.model;

import java.util.ArrayList;
import java.util.List;


public class Proprietario extends Pessoa {
    
    private List<Residencia> propriedades = new ArrayList<>();
    
    
    public Proprietario() {}

    public Proprietario(int idade, String nome, String rg, String cpf) {
        super(idade, nome, rg, cpf);
    }
    
    public List<Residencia> getPropriedades() {
        return propriedades;
    }

    public void addResidencia(Residencia residencia) {
        propriedades.add(residencia);
    }
    
}
