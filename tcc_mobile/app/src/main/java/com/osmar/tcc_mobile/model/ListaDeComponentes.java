package com.osmar.tcc_mobile.model;

import java.util.List;

public class ListaDeComponentes {

    private List<ComponenteAdpterLed> leds;
    private List<ComponenteAdpterSensor> temperatureSensors;

    public List<ComponenteAdpterLed> getLeds() {
        return leds;
    }

    public void setLeds(List<ComponenteAdpterLed> leds) {
        this.leds = leds;
    }

    public List<ComponenteAdpterSensor> getTemperatureSensors() {
        return temperatureSensors;
    }

    public void setTemperatureSensors(List<ComponenteAdpterSensor> temperatureSensors) {
        this.temperatureSensors = temperatureSensors;
    }
}
