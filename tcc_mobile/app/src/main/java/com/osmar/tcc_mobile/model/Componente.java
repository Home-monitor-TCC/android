package com.osmar.tcc_mobile.model;

public class Componente {
    private String name;
    private String description;
    private int type;
    private int pin;

    public Componente(String name, String description, int type, int pin) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
