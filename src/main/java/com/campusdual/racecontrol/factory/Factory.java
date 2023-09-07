package com.campusdual.racecontrol.factory;

import com.campusdual.racecontrol.Car;
import com.campusdual.racecontrol.Garage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public static void readJSON(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("garages.json")){

            Object obj = jsonParser.parse(reader);

            JSONArray garageList = (JSONArray) obj;
            System.out.println("El archivo contiene el siguiente JSON: ");
            System.out.println(garageList);

            for (Object garages : garageList){
                showInfoGarages((JSONObject) garages);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
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

    public static List<Garage> createDDBB(){
        List<Garage> mainGarageList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("garages.json")){

            Object obj = jsonParser.parse(reader);

            JSONArray garageList = (JSONArray) obj;

            for (Object garages : garageList){
                Garage garage;
                garage = createObjects((JSONObject) garages);
                mainGarageList.add(garage);

            }
            System.out.println("Se ha generado la BBDD correctamente");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        return mainGarageList;
    }

    public static Garage createObjects(JSONObject jsonObject){
        List<Car> carList = new ArrayList();
        JSONObject garage = (JSONObject) jsonObject.get(Factory.GARAGE);
        //Create Garages
        Long garageCode = (Long)garage.get(Factory.CODE);
        String garageName = (String)garage.get(Factory.NAME);
        JSONArray carsList = (JSONArray) garage.get(Factory.CARS);

        Garage newGarage = new Garage(garageCode, garageName);

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



}
