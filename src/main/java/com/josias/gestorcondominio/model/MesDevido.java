package com.josias.gestorcondominio.model;

public class MesDevido {
    private int id, mes, ano;
    private double valor;
    private Residencia residencia;
    
    public MesDevido(){}

    public MesDevido(int mes, int ano, double valor, Residencia residencia) {
        this.mes = mes;
        this.ano = ano;
        this.valor = valor;
        this.residencia = residencia;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
    }
    
}
