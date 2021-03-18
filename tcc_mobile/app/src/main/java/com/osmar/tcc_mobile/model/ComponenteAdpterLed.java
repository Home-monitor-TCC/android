package com.osmar.tcc_mobile.model;



public class ComponenteAdpterLed extends ComponenteAdpter {


    private Boolean state;


    public ComponenteAdpterLed(String id, String name, String description, int type, int pin, Integer imgEstadoResource,Boolean state) {
        super(id, name, description, type, pin, imgEstadoResource);
        this.state=state;

    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}