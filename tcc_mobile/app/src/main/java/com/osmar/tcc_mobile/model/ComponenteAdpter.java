package com.osmar.tcc_mobile.model;

import java.io.Serializable;

public class ComponenteAdpter implements Serializable {
    protected String id;
    protected String name;
    protected String description;
    protected int type;
    protected int pin;
    protected Integer imgEstadoResource;

    public ComponenteAdpter(){

    }

    public ComponenteAdpter(String id, String name, String description, int type, int pin, Integer imgEstadoResource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pin = pin;
        this.imgEstadoResource = imgEstadoResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getImgEstadoResource() {
        return imgEstadoResource;
    }

    public void setImgEstadoResource(Integer imgEstadoResource) {
        this.imgEstadoResource = imgEstadoResource;
    }
}
