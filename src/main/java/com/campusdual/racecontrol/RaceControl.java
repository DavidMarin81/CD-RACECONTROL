package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaceControl {


    public static void main(String[] args) {

        //Se crea la base de datos
        List<Garage> garageList = Factory.createDDBB();

        startApp();



    }

    public static void mainMenu(){
        System.out.println("=======================================");
        System.out.println("==      WELCOME TO RACE CONTROL      ==");
        System.out.println("=======================================");
        System.out.println("Select an option:");
        System.out.println("\t1.- Championship");
        System.out.println("\t2.- Race");
        System.out.println("\t0.- Exit");
        System.out.println("=======================================");
    }

    public static void startApp(){
        mainMenu();
        int option;
        do {
            option = Input.integer("Introduce an option: ");
            switch (option) {
                case 0:
                    System.out.println("Thanks for using our app!!!");
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }



}
