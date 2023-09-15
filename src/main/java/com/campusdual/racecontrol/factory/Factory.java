package com.campusdual.racecontrol.factory;

import com.campusdual.racecontrol.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {
    //Garages
    private static final String GARAGE = "garage";
    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String CARS = "cars";

    //Cars
    private static final String ID = "id";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String STICKER = "sticker";
    private static final String NUMBER = "number";
    //Tournaments
    private static final String RACES = "races";

    private Factory(){

    }

    public static List<Garage> getConnectionGarage(){
        return createGarageDDBB();
    }

    public static List<Tournament> getConnectionTournament(){
        return createTournamentsDDBB();
    }

    public static void showInfoGarages(JSONObject jsonObject){
        JSONObject garage = (JSONObject) jsonObject.get(Factory.GARAGE);
        System.out.println("Garages: ");
        Long garageCode = (Long) garage.get(Factory.CODE);
        System.out.println("\tCode: " + garageCode);
        String garageName = (String)garage.get(Factory.NAME);
        System.out.println("\tName: " + garageName);
        JSONArray carsList = (JSONArray) garage.get(Factory.CARS);
        System.out.println("\tCoches:");

        for(Object car : carsList){
            JSONObject coche = (JSONObject) car;
            System.out.println("\t\t\tCoche:");
            System.out.println("\t\t\t\tId: " + coche.get(Factory.ID));
            System.out.println("\t\t\t\tMarca: " + coche.get(Factory.BRAND));
            System.out.println("\t\t\t\tModelo: " + coche.get(Factory.MODEL));
            System.out.println("\t\t\t\tSticker: " + coche.get(Factory.STICKER));
        }

    }

    private static List<Garage> createGarageDDBB(){
        List<Garage> mainGarageList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("garages.json")){

            Object obj = jsonParser.parse(reader);

            JSONArray garageList = (JSONArray) obj;

            for (Object garages : garageList){
                Garage garage = createObjects((JSONObject) garages);
                mainGarageList.add(garage);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return mainGarageList;
    }

    private static Garage createObjects(JSONObject jsonObject){
        //Create Garages
        Long garageCode = (Long)jsonObject.get(Factory.CODE);
        String garageName = (String)jsonObject.get(Factory.NAME);
        JSONArray carsList = (JSONArray) jsonObject.get(Factory.CARS);

        Garage newGarage = new Garage(garageCode, garageName);

        List<Car> carList = new ArrayList<>();
        //Create cars
        for(Object c : carsList){
            JSONObject car = (JSONObject) c;
            Long id = (Long) car.get(Factory.ID);
            String brand = (String) car.get(Factory.BRAND);
            String model = (String) car.get(Factory.MODEL);
            String sticker = (String) car.get(Factory.STICKER);
            Car newCar = new Car(id, brand, model, sticker);
            carList.add(newCar);
        }

        newGarage.setCarList(carList);
        return newGarage;
    }

    private static List<Tournament> createTournamentsDDBB(){
        List<Tournament> mainTournamentList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("tournaments.json")){

            Object obj = jsonParser.parse(reader);

            JSONArray tournamentList = (JSONArray) obj;

            for (Object tournaments : tournamentList){
                Tournament tournament = createObjects2((JSONObject) tournaments);
                mainTournamentList.add(tournament);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return mainTournamentList;
    }

    private static Tournament createObjects2(JSONObject jsonObject){
        //Create Garages
        Long tournamentId = (Long)jsonObject.get(Factory.ID);
        String tournamentName = (String)jsonObject.get(Factory.NAME);
        JSONArray racesList = (JSONArray) jsonObject.get(Factory.RACES);

        Tournament newTournament = new Tournament(tournamentId, tournamentName);

        List<Race> raceList = new ArrayList<>();
        //Create Races
        for(Object r : racesList){
            JSONObject race = (JSONObject) r;
            Long id = (Long) race.get(Factory.ID);
            String name = (String) race.get(Factory.NAME);
            StandardRace newRace = new StandardRace(id, name);
            raceList.add(newRace);
        }

        newTournament.setRaceList(raceList);
        return newTournament;
    }

    public static void saveDDBB(List<Garage> garageList) {
        JSONArray garageListToJson = new JSONArray();
        for(Garage g : garageList){
            //Se añaden los garages
            JSONObject garageJson = new JSONObject();
            garageJson.put(Factory.CODE, g.getCode());
            garageJson.put(Factory.NAME, g.getName());
            JSONArray carListToJson = new JSONArray();
            //Se añaden los coches
            for(Car c : g.getCarList()){
                JSONObject car = new JSONObject();
                car.put(Factory.ID, c.getId());
                car.put(Factory.BRAND, c.getBrand());
                car.put(Factory.MODEL, c.getModel());
                car.put(Factory.STICKER, c.getSticker());
                carListToJson.add(car);
            }

            garageJson.put(Factory.CARS, carListToJson);
            garageListToJson.add(garageJson);
        }
        try(FileWriter file = new FileWriter("garages.json")){
            file.write(garageListToJson.toString());
            file.flush(); //Cierra el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
