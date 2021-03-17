package com.osmar.tcc_mobile.model;

public class ComponenteResposta extends Componente {
    private String id;

    public ComponenteResposta(String name, String description, int type, int pin) {
        super(name, description, type, pin);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
