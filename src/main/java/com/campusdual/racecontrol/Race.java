package com.campusdual.racecontrol;

import com.campusdual.racecontrol.factory.Factory;
import com.campusdual.racecontrol.util.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Race {
    private final static String RACE01 = "Mario Kart Stadium";
    private final static String RACE02 = "Water Park";
    private final static String RACE03 = "Sweet Sweet Canyon";
    private final static String RACE04 = "Thwomp Ruins";
    private final static String RACE05 = "Mario Circuit";
    private final static String RACE06 = "Toad Harbour";
    private final static String RACE07 = "Twisted Mansion";
    private final static String RACE08 = "Shy Guy Falls";
    private final static String RACE09 = "Sunshine Airport";
    private final static String RACE10 = "Dolphin Shoals";
    private final static String RACE11 = "Electrodome";
    private final static String RACE12 = "Mount Wario";
    private final static String RACE13 = "Cloudtop Cruise";
    private final static String RACE14 = "Bone Dry Dunes";
    private final static String RACE15 = "Bowser´s Castle";
    private final static String RACE16 = "Rainbow Road";
    private static int count = 1;
    private Long id;
    private String name;
    private List<Car> carList = new ArrayList<>();
    private List<Car> podium = new ArrayList<>();


    public Race(){

    }

    public Race(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getPodium() {
        return podium;
    }

    public void setPodium(List<Car> podium) {
        this.podium = podium;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carList=" + carList +
                ", podium=" + podium +
                '}';
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
                    StandardRace.standardRaceMenu();
                    option = 0;
                    break;
                case 2:
                    EliminatedRace.eliminatedRaceMenu();
                    option = 0;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (option != 0);
    }
    public static void singleGarageEliminationMenu(){
        List<Garage> garageList = Factory.getConnectionGarage();
        List<Car> carList = new ArrayList<>();
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
                if(Tournament.auxListRace.isEmpty()){
                    carList = Garage.selectCarsFromGarage(garage, garageList);
                    selectCircuitMenu(selectCircuits());
                    EliminatedRace.startEliminationRace(carList);
                } else {
                    List<Car> auxCarList = new ArrayList<>();
                    for(Race r : Tournament.auxListRace){
                        carList = Garage.selectCarsFromGarage(garage, garageList);
                        EliminatedRace.startEliminationRace(carList);
                    }
                }
                option = 0;
            }
        }while (option != 0);
    }
    public static void singleGarageStandardMenu(){
        List<Garage> garageList = Factory.getConnectionGarage();
        List<Car> carList = new ArrayList<>();
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
                option = 0;
                if(Tournament.auxListRace.isEmpty()){
                    carList = Garage.selectCarsFromGarage(garage, garageList);
                    selectCircuitMenu(selectCircuits());
                    StandardRace.startRace(carList);
                } else {
                    for(Race c : Tournament.auxListRace){
                        System.out.println("=======================================");
                        System.out.println("Empieza la carrera:" + c.getName());
                        carList = Garage.selectCarsFromGarage(garage, garageList);
                        StandardRace.startRace(carList);
                    }
                }
            }

        }while (option != 0);
    }
    public static void severalEliminatedRaceMenu(){
        List<Car> auxCarList = new ArrayList<>();
        List<Garage> garageList = Factory.getConnectionGarage();
        List<String> garagesToRace = new ArrayList<>();
        List<Car> listaDeCochesAuxiliar = new ArrayList<>();
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
                    Garage.checkGaragesRepetead(Garage.GARAGE01);
                    garagesToRace.add(Garage.GARAGE01);
                    break;
                case "2":
                    Garage.checkGaragesRepetead(Garage.GARAGE02);
                    garagesToRace.add(Garage.GARAGE02);
                    break;
                case "3":
                    Garage.checkGaragesRepetead(Garage.GARAGE03);
                    garagesToRace.add(Garage.GARAGE03);
                    break;
                case "4":
                    Garage.checkGaragesRepetead(Garage.GARAGE04);
                    garagesToRace.add(Garage.GARAGE04);
                    break;
                case "5":
                    Garage.checkGaragesRepetead(Garage.GARAGE05);
                    garagesToRace.add(Garage.GARAGE05);
                    break;
                case "6":
                    Garage.checkGaragesRepetead(Garage.GARAGE06);
                    garagesToRace.add(Garage.GARAGE06);
                    break;
                case "7":
                    Garage.checkGaragesRepetead(Garage.GARAGE07);
                    garagesToRace.add(Garage.GARAGE07);
                    break;
                case "8":
                    Garage.checkGaragesRepetead(Garage.GARAGE08);
                    garagesToRace.add(Garage.GARAGE08);
                    break;
                case "S":
                    auxCarList = Garage.selectOneCarByGarage(garageList);
                    List<Car> carsListToRace = new ArrayList<>();

                    for(Car c : auxCarList){
                        for(String s : garagesToRace){
                            if(c.getSticker().equals(s)){
                                carsListToRace.add(c);
                            }
                        }
                    }
                    selectCircuitMenu(selectCircuits());
                    if(Tournament.auxListRace.isEmpty()){
                        EliminatedRace.startEliminationRace(carsListToRace);
                    } else {
                        for(Race r : Tournament.auxListRace){
                            System.out.println("Empieza la eliminatorio en " + r.getName());
                            for(Car c : carsListToRace){
                                c.restartCar();
                                listaDeCochesAuxiliar.add(c);
                            }
                            EliminatedRace.startEliminationRace(listaDeCochesAuxiliar);
                        }
                    }
                    option = "0";
                    Garage.auxGarageList.clear();
                    resetCarKm(carsListToRace);
                    break;
                case "C":
                    Garage.showSeveralGarages();
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (!option.equals("0"));
    }
    public static void severalStandardRaceMenu(){
        List<Car> listaDeCochesAuxiliar = new ArrayList<>();
        List<Car> auxCarList = new ArrayList<>();
        List<Garage> garageList = Factory.getConnectionGarage();
        List<String> garagesToRace = new ArrayList<>();
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
                    Garage.checkGaragesRepetead(Garage.GARAGE01);
                    garagesToRace.add(Garage.GARAGE01);
                    break;
                case "2":
                    Garage.checkGaragesRepetead(Garage.GARAGE02);
                    garagesToRace.add(Garage.GARAGE02);
                    break;
                case "3":
                    Garage.checkGaragesRepetead(Garage.GARAGE03);
                    garagesToRace.add(Garage.GARAGE03);
                    break;
                case "4":
                    Garage.checkGaragesRepetead(Garage.GARAGE04);
                    garagesToRace.add(Garage.GARAGE04);
                    break;
                case "5":
                    Garage.checkGaragesRepetead(Garage.GARAGE05);
                    garagesToRace.add(Garage.GARAGE05);
                    break;
                case "6":
                    Garage.checkGaragesRepetead(Garage.GARAGE06);
                    garagesToRace.add(Garage.GARAGE06);
                    break;
                case "7":
                    Garage.checkGaragesRepetead(Garage.GARAGE07);
                    garagesToRace.add(Garage.GARAGE07);
                    break;
                case "8":
                    Garage.checkGaragesRepetead(Garage.GARAGE08);
                    garagesToRace.add(Garage.GARAGE08);
                    break;
                case "S":
                    auxCarList = Garage.selectOneCarByGarage(garageList);
                    List<Car> carsListToRace = new ArrayList<>();

                    for(Car c : auxCarList){
                        for(String s : garagesToRace){
                            if(c.getSticker().equals(s)){
                                carsListToRace.add(c);
                            }
                        }
                    }

                    if(Tournament.auxListRace.isEmpty()){
                        selectCircuitMenu(selectCircuits());
                        StandardRace.startRace(carsListToRace);
                    } else {
                        for(Race r : Tournament.auxListRace){
                            System.out.println("=======================================");
                            System.out.println("\t\t" + r.getName().toUpperCase());
                            System.out.println("=======================================");
                            for(Car c : carsListToRace){
                                c.restartCar();
                                listaDeCochesAuxiliar.add(c);
                            }
                            StandardRace.startRace(carsListToRace);
                        }
                    }

                    option = "0";
                    Garage.auxGarageList.clear();
                    resetCarKm(carsListToRace);
                    break;
                case "C":
                    Garage.showSeveralGarages();
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
        }while (!option.equals("0"));
    }
    public static void selectCircuitMenu(List<Race> raceList){
        int contador = 1;
        int option;
        String race = "";
        do {
            System.out.println("=======================================");
            System.out.println("==           CHOOSE A RACE           ==");
            System.out.println("=======================================");
            for(Race r : raceList){
                System.out.println("\t\t" + contador + " -> " + r.name);
                contador++;
            }
            System.out.println("\t\t0 -> Exit");
            System.out.println("=======================================");
            String garage = "";
            option = Input.integer("Select a race: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    race = Race.RACE01;
                    break;
                case 2:
                    race = Race.RACE02;
                    break;
                case 3:
                    race = Race.RACE03;
                    break;
                case 4:
                    race = Race.RACE04;
                    break;
                case 5:
                    race = Race.RACE05;
                    break;
                case 6:
                    race = Race.RACE06;
                    break;
                case 7:
                    race = Race.RACE07;
                    break;
                case 8:
                    race = Race.RACE08;
                    break;
                case 9:
                    race = Race.RACE09;
                    break;
                case 10:
                    race = Race.RACE10;
                    break;
                case 11:
                    race = Race.RACE11;
                    break;
                case 12:
                    race = Race.RACE12;
                    break;
                case 13:
                    race = Race.RACE13;
                    break;
                case 14:
                    race = Race.RACE14;
                    break;
                case 15:
                    race = Race.RACE15;
                    break;
                case 16:
                    race = Race.RACE16;
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }

            if(option != 0){
                option = 0;
            }


        }while (option != 0);
    }
    public static List<Car> raceClassification(List<Car> carList){
        List<Car> carsInPodium = new ArrayList<>();
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
                c.setPositionInRace(position);
                carsInPodium.add(c);
            }
        }
        Podium.addPuntuation(carsInPodium);
        Podium.showPuntuation(carsInPodium);

        carList.sort(Comparator.comparing(Car::getPuntuation).reversed());
        System.out.println("==========================================");
        System.out.println("         GENERAL CLASSIFICATION");
        System.out.println("==========================================");
        int pos = 0;
        for(Car c : carList){
            System.out.println(++pos + "º -> " + c.getBrand() + "(" + c.getId() + ") = " + c.getPuntuation() + " ptos");
        }
        System.out.println("==========================================");


        for(Car c : carList){
            c.restartCar();
        }

        return carsInPodium;
    }

    public static void getPuntuationToCar(List<Car> carList){
        System.out.println("Se ordena la lista del primero al tercero");
        carList.sort(Comparator.comparing(Car::getDistance).reversed());

    }

    public static void resetCarKm(List<Car> carList){
        for(Car c : carList){
            c.restartCar();
        }
    }
    public static void startRace(List<Car> carList) {
        for(Car c : carList){
            c.restartCar();
        }
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
        raceClassification(carList);

    }
    public static List<Race> selectCircuits(){
        List<Race> raceList = new ArrayList<>();
        List<Tournament> tournamentList = Factory.getConnectionTournament();
        for(Tournament t : tournamentList){
            for(Race r : t.getRaceList()){
                raceList.add(r);
            }
        }

        return raceList;
    }
    public static List<Race> selectRacesForTournament(String tournamentName){
        List<Race> raceList = new ArrayList<>();
        for(Tournament t : Factory.getConnectionTournament()){
            if(tournamentName.equals(t.getName())){
                for(Race r : t.getRaceList()){
                    raceList.add(r);
                }
            }
        }
        return raceList;
    }


}
