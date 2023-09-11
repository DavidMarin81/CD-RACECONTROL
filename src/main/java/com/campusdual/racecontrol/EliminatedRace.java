package com.campusdual.racecontrol;

import com.campusdual.racecontrol.util.Input;

import java.util.Comparator;
import java.util.List;

public class EliminatedRace extends Race{
    private int minToStart = 10; //in minutes
    public EliminatedRace() {
    }
    public static void eliminatedRaceMenu(){
        int option;
        do {
            System.out.println("=======================================");
            System.out.println("        ELIMINATED RACE OPTIONS        ");
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
                    Race.singleGarageEliminationMenu();
                    option = 0;
                    break;
                case 2:
                    Race.severalEliminatedRaceMenu();
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }

    public static void startEliminationRace(List<Car> carList){
        int lap = 0;
        int position = 1;
        while (carList.size() > 1){
            System.out.println("=================================");
            System.out.println("        LAP " + lap);
            System.out.println("=================================");

            for(Car c : carList){
                c.drive();
            }
            carList.sort(Comparator.comparing(Car::getDistance).reversed());
            position = 1;
            for(Car c : carList){
                System.out.println(position + " -> ("
                        + c.getId() + ")"
                        + c.getBrand()
                        + "(" + c.getSticker() + ") = "
                        + Math.round((c.getDistance()*100.0) / 100.0) + " kms)");
                position++;
            }
            if(lap > 0){
                carList.sort(Comparator.comparing(Car::getDistance).reversed());
                System.out.println("=================================");
                System.out.println("Se elimina a: " + carList.get(carList.size()-1));
                carList.remove(carList.size()-1);
            }
            lap += 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        raceClassification(carList);
    }
}
