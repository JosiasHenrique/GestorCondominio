package com.josias.gestorcondominio.model;

public abstract class Pessoa {
    
    private int id, idade;
    private String nome, rg, cpf;
    
    public Pessoa(){}

    public Pessoa(int id, int idade, String nome, String rg, String cpf) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
