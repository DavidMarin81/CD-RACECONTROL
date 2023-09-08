package com.campusdual.racecontrol;

import com.campusdual.racecontrol.util.Util;

public class Car {
    private static final int MAX_SPEED = 200;
    private int speedmether = 0;
    private Long id, number;
    private String brand, model, sticker;
    private int kmRecorridos = 0;
    private double distance = 0.0;

    public Car(Long id, String brand, String model, String sticker) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.sticker = sticker;
    }

    public int getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(int kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSticker() {
        return sticker;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public double getDistance() {
        return distance;
    }

    public void speedUp(){
        if(this.speedmether < MAX_SPEED){
            this.speedmether += 10;
        }
    }

    public void speedDown(){
        if(this.speedmether > 0){
            this.speedmether -= 10;
        }
    }

    public void updateDistance(){
        this.distance += speedmether * 16.67;
    }

    public void drive(){
        int state = Util.randomGenerator(-1, 1);
        if(state > -1){
            speedUp();
        } else {
            speedDown();
        }
        updateDistance();
    }

    @Override
    public String toString() {
        return "\n\t\tCar{" +
                "\n\t\t\tid=" + id +
                "\n\t\t\tnumber=" + number +
                "\n\t\t\tbrand='" + brand + '\'' +
                "\n\t\t\tmodel='" + model + '\'' +
                "\n\t\t\tsticker='" + sticker + '\'' +
                '}';
    }


}
