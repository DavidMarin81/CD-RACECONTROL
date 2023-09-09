package com.campusdual.racecontrol;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

}
