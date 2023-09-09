package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Input;
import com.campusdual.racecontrol.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RaceControl {
    public static List<Garage> garageList;
    public static List<Car> auxCarList = new ArrayList<>();
    public static List<Garage> auxGarageList = new ArrayList<>();

    public static int type = 0;

    public static void main(String[] args) {

        //Se crea la base de datos
        garageList = Factory.createGarageDDBB();

        //Empezando con la aplicacion
        startApp();
    }
    public static Car selectRandomGarageCar(String garageName){
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
    public static List<Car> selectOneCarByGarage(List<Garage> garageList){
        Car auxCar;
        for(Garage g : garageList){
            auxCar = selectRandomGarageCar(g.getName());
            auxCarList.add(auxCar);
        }
        return auxCarList;
    }
    public static List<Car> selectCarsFromGarage(String garageName){
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
        raceClassification(auxCarList);
    }
    public static void startEliminationRace(List<Car> carList) {
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
        raceClassification(auxCarList);
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
    public static void raceMenu(){
        int option;
        do {
            System.out.println("=======================================");
            System.out.println("==            RACE OPTIONS           ==");
            System.out.println("=======================================");
            System.out.println("\t1.- Standard Race");
            System.out.println("\t2.- Elimination Race");
            System.out.println("\t0.- Exit");
            System.out.println("=======================================");
            option = Input.integer("Select an option: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    type = 1;
                    standardRaceMenu("STANDARD");
                    option = 0;
                    break;
                case 2:
                    type = 2;
                    standardRaceMenu("ELIMINATION");
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }
    public static void standardRaceMenu(String raceType){
        int option;
        do {
            System.out.println("=======================================");
            System.out.println("     " + raceType + " RACE OPTIONS       ");
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
                    auxCarList.clear();
                    singleGarageMenu();
                    option = 0;
                    break;
                case 2:
                    auxGarageList.clear();
                    severalRaceMenu();
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }
    public static void singleGarageMenu(){
        int contador = 1;
        int option;
        do {
        System.out.println("=======================================");
        System.out.println("==         SINGLE RACE OPTIONS       ==");
        System.out.println("=======================================");
        for(Garage g : garageList){
            System.out.println("\t\t" + contador + " -> " + g.getName());
            contador++;
        }
        System.out.println("\t\t0 -> Exit");
        System.out.println("=======================================");
        String garage = "";
            option = Input.integer("Select a garage number: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    garage = Garage.GARAGE01;
                    break;
                case 2:
                    garage = Garage.GARAGE02;
                    break;
                case 3:
                    garage = Garage.GARAGE03;
                    break;
                case 4:
                    garage = Garage.GARAGE04;
                    break;
                case 5:
                    garage = Garage.GARAGE05;
                    break;
                case 6:
                    garage = Garage.GARAGE06;
                    break;
                case 7:
                    garage = Garage.GARAGE07;
                    break;
                case 8:
                    garage = Garage.GARAGE08;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }

            if(option != 0){
                auxCarList = selectCarsFromGarage(garage);
                startSingleRaceMenu();
                option = 0;
            }

        }while (option != 0);
    }
    public static void startSingleRaceMenu() {
        int option;
        do {
            System.out.println("=======================================");
            System.out.println("==         START THE RACE!!!         ==");
            System.out.println("=======================================");
            System.out.println("\t1.- START NOW!!!");
            System.out.println("\t0.- Exit");
            option = Input.integer("Select an option: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    if(type == 1){
                        startRace(auxCarList);
                    } else if(type == 2){
                        startEliminationRace(auxCarList);
                    }
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }
    public static void severalRaceMenu(){
        String option;
        do {
            System.out.println("=======================================");
            System.out.println("==    SEVERAL GARAGE RACE OPTIONS    ==");
            System.out.println("=======================================");
            System.out.println("\tSelect Garages:");
            for(Garage g : garageList){
                System.out.println("\t\t" + g.getCode() + " -> " + g.getName());
            }
            System.out.println("\t\t0 -> Exit");
            System.out.println();
            System.out.println("Press 'C' to check the garages");
            System.out.println("Press 'S' to start!!!");
            System.out.println("=======================================");

            option = Input.string("Select a garage to include in the race: ");
            switch (option.toUpperCase()) {
                case "0":
                    break;
                case "1":
                    checkGaragesRepetead(Garage.GARAGE01);
                    break;
                case "2":
                    checkGaragesRepetead(Garage.GARAGE02);
                    break;
                case "3":
                    checkGaragesRepetead(Garage.GARAGE03);
                    break;
                case "4":
                    checkGaragesRepetead(Garage.GARAGE04);
                    break;
                case "5":
                    checkGaragesRepetead(Garage.GARAGE05);
                    break;
                case "6":
                    checkGaragesRepetead(Garage.GARAGE06);
                    break;
                case "7":
                    checkGaragesRepetead(Garage.GARAGE07);
                    break;
                case "8":
                    checkGaragesRepetead(Garage.GARAGE08);
                    break;
                case "S":
                    selectOneCarByGarage(auxGarageList);
                    startSingleRaceMenu();
                    option = "0";
                    break;
                case "C":
                    showSeveralGarages();
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (!option.equals("0"));
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
        for(Garage g : garageList){
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
    public static void startApp(){
        int option = 0;
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
                    break;
                case 2:
                    raceMenu();
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
        System.out.println("Thanks for using our app!!!");
    }
}
