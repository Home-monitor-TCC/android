package com.osmar.tcc_mobile.model;

public class ComponenteResposta extends Componente {
    private String id;
    public ComponenteResposta(String nome, String descricao, int tipo, int pino,String id) {
        super(nome, descricao, tipo, pino);
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
