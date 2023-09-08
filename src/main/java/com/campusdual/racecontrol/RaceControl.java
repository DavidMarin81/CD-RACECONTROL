package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Input;
import com.campusdual.racecontrol.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RaceControl {
    public static List<Garage> garageList;

    public static void main(String[] args) {

        //Se crea la base de datos
        garageList = Factory.createGarageDDBB();

        Car c1 = selectRandomGarageCar(garageList, "Niece Motorsports");
        Car c2 = selectRandomGarageCar(garageList, "RFK Racing");
        Car c3 = selectRandomGarageCar(garageList, "Richard Childress Racing");

        List<Car> carList = new ArrayList<>();
        carList.add(c1);
        carList.add(c2);
        carList.add(c3);

        startRace(carList);
        raceClassification(carList);

    }

    public static Car selectRandomGarageCar(List<Garage> carList, String garageName){
        Car carSelected = null;
        List<Car> carsFromGarage = new ArrayList<>();
        for(Garage g : garageList){
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

    public static void startRace(List<Car> carList) {
        int lap = 0;
        while (lap <= 10){
            for(Car c : carList){
                c.drive();
            }
            lap += 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void raceClassification(List<Car> carList){
        System.out.println("==========================================");
        System.out.println("          RACE CLASSIFICATION");
        System.out.println("==========================================");
        int position = 1;
        double kmAux = 0;
        carList.sort(Comparator.comparing(Car::getDistance).reversed());
        for (Car c : carList){
            if(kmAux == 0){
                kmAux = c.getDistance();
            } else {
                if(kmAux > c.getDistance()){
                    kmAux = c.getDistance();
                    position++;
                }
            }
            if (position <= 3) {
                System.out.println(position + "º -> "
                        + c.getBrand()
                        + "(" + c.getId() + ") "
                        + " -> Team: " + c.getSticker()
                        + " (" + Math.round((c.getDistance()*100.0) / 100.0) + " kms)");
            }
        }
        resetCarKm(carList);
    }

    public static void resetCarKm(List<Car> carList){
        for(Car c : carList){
            c.setKmRecorridos(0);
        }
    }

    public static void mainMenu(){
        System.out.println("=======================================");
        System.out.println("==      WELCOME TO RACE CONTROL      ==");
        System.out.println("=======================================");
        System.out.println("\t1.- Championship");
        System.out.println("\t2.- Race");
        System.out.println("\t0.- Exit");
        System.out.println("=======================================");
    }

    public static void raceMenu(){
        System.out.println("=======================================");
        System.out.println("==            RACE OPTIONS           ==");
        System.out.println("=======================================");
        System.out.println("\t1.- Standard Race");
        System.out.println("\t2.- Elimination Race");
        System.out.println("\t0.- Exit");
        System.out.println("=======================================");
    }

    public static void raceStandardMenu(){
        System.out.println("=======================================");
        System.out.println("==       STANDARD RACE OPTIONS       ==");
        System.out.println("=======================================");
        System.out.println("\t1.- Single Garage");
        System.out.println("\t2.- Several Garage");
        System.out.println("\t0.- Exit");
        System.out.println("=======================================");
    }

    public static void raceStandartSimpleGarageMenu(){
        System.out.println("=======================================");
        System.out.println("==     SIMPLE GARAGE RACE OPTIONS    ==");
        System.out.println("=======================================");
        System.out.println("\tSelect a Garage:");
        for(Garage g : garageList){
            System.out.println("\t\t" + g.getCode() + " -> " + g.getName());
        }
        System.out.println("\t0.- Exit");
        System.out.println("=======================================");
    }

    public static void raceOptions(){
        raceMenu();
        int option = Input.integer("Select an option: ");
        switch (option){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Wrong option!!!");
                break;
        }
    }

    public static void startApp(){
        mainMenu();
        int option;
        do {
            option = Input.integer("Select an option: ");
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
