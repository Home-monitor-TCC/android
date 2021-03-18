package com.osmar.tcc_mobile.model;

public class ComponenteAdpterSensor extends  ComponenteAdpter{
    private float temperature;

    public ComponenteAdpterSensor(String id, String name, String description, int type, int pin, Integer imgEstadoResource, float temperature) {
        super(id, name, description, type, pin, imgEstadoResource);
        this.temperature = temperature;
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
