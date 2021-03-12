package com.osmar.tcc_mobile.model;

public class Componente {
    private String nome;
    private String descricao;
    private int tipo;
    private int pino;

    public Componente(String nome, String descricao, int tipo, int pino) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.pino = pino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPino() {
        return pino;
    }

    public void setPino(int pino) {
        this.pino = pino;
    }
}
