package com.campusdual.racecontrol;

import java.util.List;

public class Race {
    private int id;
    private String name;
    private String style;
    private List<Garage> garageList;
    private List<Car> carList;

    public Race(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<Garage> getGarageList() {
        return garageList;
    }

    public void setGarageList(List<Garage> garageList) {
        this.garageList = garageList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
