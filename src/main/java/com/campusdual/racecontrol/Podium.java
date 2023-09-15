package com.campusdual.racecontrol;

import java.util.Comparator;
import java.util.List;

public class Podium {
    private List<Car> carList;
    private int puntuation;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Podium(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public int getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public static void addPuntuation(List<Car> carList){
        int puntuation = 15;
        carList.sort(Comparator.comparing(Car::getDistance).reversed());
        for(Car c : carList){
            if(c.getPositionInRace() == 1){
                c.setPuntuation(c.getPuntuation() + 15);
            } else if(c.getPositionInRace() == 2){
                c.setPuntuation(c.getPuntuation() + 10);
            } else {
                c.setPuntuation(c.getPuntuation() + 5);
            }
        }
    }
    public static void showPuntuation(List<Car> carList){
        int puntuation = 15;
        carList.sort(Comparator.comparing(Car::getDistance).reversed());
        for(Car c : carList){
            if(c.getPositionInRace() == 1){
                System.out.println(c.getPositionInRace() + "º -> " + c.getBrand() + "(" + c.getId() + ") = " + puntuation + " ptos");
            } else if(c.getPositionInRace() == 2){
                puntuation -= 5;
                System.out.println(c.getPositionInRace() + "º -> " + c.getBrand() + "(" + c.getId() + ") = " + puntuation + " ptos");
            } else {
                puntuation -= 5;
                System.out.println(c.getPositionInRace() + "º -> " + c.getBrand() + "(" + c.getId() + ") = " + puntuation + " ptos");
            }
        }
    }

}
