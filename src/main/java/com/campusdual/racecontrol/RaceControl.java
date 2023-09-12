package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Input;
import com.campusdual.racecontrol.util.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RaceControl {
    public static List<Garage> garageList;
    public static List<Tournament> tournamentList;

    public static void main(String[] args) {

        //Se crea la base de datos
        garageList = Factory.createGarageDDBB();
        tournamentList = Factory.createTournaments();

        //Empezando con la aplicacion
        startApp();
    }
    public static void startApp(){
        int option = 0;
        Tournament.auxListRace.clear();
        do {
            System.out.println("\n\n\n");
            System.out.println("=======================================");
            System.out.println("==      WELCOME TO RACE CONTROL      ==");
            System.out.println("=======================================");
            System.out.println("\t1.- Championship");
            System.out.println("\t2.- Race");
            System.out.println("\t0.- Exit");
            System.out.println("=======================================");
            option = Input.integer("Select an option menu principal: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    Tournament.showMenuTournament();
                    break;
                case 2:
                    Race.raceMenu();
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
        System.out.println("Thanks for using our app!!!");
        Factory.saveDDBB(garageList);

    }



}
