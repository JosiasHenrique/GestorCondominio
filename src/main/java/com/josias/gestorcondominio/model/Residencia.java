package com.josias.gestorcondominio.model;

import java.util.ArrayList;
import java.util.List;

public class Residencia {
    
    private int id, numero;
    private String rua, cep;
    private Proprietario proprietario;
    private List<Morador> moradores = new ArrayList<>();
    private List<MesDevido> mesesDevidos = new ArrayList<>();

    public Residencia(int id, int numero, String rua, String cep, Proprietario proprietario) {
        this.id = id;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public List<MesDevido> getMesesDevidos() {
        return mesesDevidos;
    }
    
    
}
