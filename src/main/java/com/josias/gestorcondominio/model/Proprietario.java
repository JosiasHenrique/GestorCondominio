package com.josias.gestorcondominio.model;

import java.util.ArrayList;
import java.util.List;


public class Proprietario extends Pessoa {
    
    private List<Residencia> propriedades = new ArrayList<>();

    public Proprietario() {}
    
    public List<Residencia> getPropriedades() {
        return propriedades;
    }
    
}
