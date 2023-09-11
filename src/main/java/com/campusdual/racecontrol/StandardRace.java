package com.campusdual.racecontrol;

import com.campusdual.racecontrol.util.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StandardRace extends Race{
    private int duration = 180; //in minutes
    public StandardRace(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return super.toString() + "\nStandardRace{" +
                "duration=" + duration +
                '}';
    }

    public static void standardRaceMenu(){
        int option;
        do {
            System.out.println("=======================================");
            System.out.println("        STANDARD RACE OPTIONS          ");
            System.out.println("=======================================");
            System.out.println("\t1.- Single Garage");
            System.out.println("\t2.- Several Garage");
            System.out.println("\t0.- Exit");
            System.out.println("=======================================");
            option = Input.integer("Select an option: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    Race.singleGarageStandardMenu();
                    option = 0;
                    break;
                case 2:
                    Race.severalStandardRaceMenu();
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }



}
