package com.campusdual.racecontrol;

public class Car {
    private static final String ID = "id";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String STICKER = "sticker";
    private static final String NUMBER = "number";

    private static final int MAX_SPEED = 250;

    private Long id, number;
    private String brand, model, sticker;

    public Car(Long id, String brand, String model, String sticker) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.sticker = sticker;
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
