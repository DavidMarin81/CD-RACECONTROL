package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Garage {
    public static final String GARAGE01 = "JD Motorsport";
    public static final String GARAGE02 = "23XI Racing";
    public static final String GARAGE03 = "RFK Racing";
    public static final String GARAGE04 = "Niece Motorsports";
    public static final String GARAGE05 = "Richard Childress Racing";
    public static final String GARAGE06 = "Team Penske";
    public static final String GARAGE07 = "Trackhouse Racing";
    public static final String GARAGE08 = "Steward Hass Racing";
    private long code;
    private String name;
    private List<Car> carList;

    public static List<Garage> auxGarageList = new ArrayList<>();

    public Garage(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "\n\tcode=" + code +
                "\n\tname='" + name + '\'' +
                "\n\tcarList=" + carList +
                '}';
    }

    public static List<Car> selectCarsFromGarage(String garageName, List<Garage> garageList){
        List<Car> carsFromGarage = new ArrayList<>();
        for(Garage g : garageList){
            if(g.getName().equals(garageName)){
                for(Car c : g.getCarList()){
                    carsFromGarage.add(c);
                }
            }
        }
        return carsFromGarage;
    }

    public static List<Car> selectOneCarByGarage(List<Garage> garageList){
        List<Car> auxCarList = new ArrayList<>();
        Car auxCar;
        for(Garage g : garageList){
            auxCar = selectRandomGarageCar(g.getName());
            auxCarList.add(auxCar);
        }
        return auxCarList;
    }

    public static Car selectRandomGarageCar(String garageName){
        Car carSelected = null;
        List<Car> carsFromGarage = new ArrayList<>();
        for(Garage g : Factory.garageList){
            if(g.getName().equals(garageName)){
                for(Car c : g.getCarList()){
                    carsFromGarage.add(c);
                }
            }
        }
        int randomNumber = Util.randomGenerator(1, carsFromGarage.size());
        for(Car car : carsFromGarage){
            if(car.getId() == randomNumber){
                carSelected = car;
            }
        }
        return carSelected;
    }

    public static void showSeveralGarages() {
        if (auxGarageList.isEmpty()) {
            System.out.println("\t\tGarages empties!!!");
        } else {
            System.out.println("\tGarages introduced: ");
            for (Garage g : auxGarageList) {
                System.out.println("\t\t" + g.getName());
            }
        }
    }

    public static void checkGaragesRepetead(String garageName) {
        boolean exist = false;
        for(Garage g : RaceControl.garageList){
            if(g.getName().equals(garageName)){
                if(auxGarageList.isEmpty()){
                    auxGarageList.add(g);
                    System.out.println("\tThe garage has be introduced correctly");
                } else {
                    for(Garage g2: auxGarageList){
                        if(garageName.equals(g2.getName())){
                            exist = true;
                        }
                    }
                    if(!exist){
                        auxGarageList.add(g);
                        System.out.println("\tThe garage has be introduced correctly");
                    } else {
                        System.out.println("\tThe garage is in the list");
                    }
                }
            }
        }
    }

}
