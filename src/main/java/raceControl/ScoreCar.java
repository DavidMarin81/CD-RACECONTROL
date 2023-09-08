package raceControl;

import com.campusdual.racecontrol.Car;
import com.campusdual.racecontrol.util.Input;
import com.campusdual.racecontrol.util.Util;

import java.util.Random;

public class ScoreCar implements Comparable<ScoreCar>{

    private String brand;
    private String model;
    private String garageName = "";
    public static final int MAX_VELOCITY = 200;
    private int speedmether = 0;

    private double distance = 0.0;

    public ScoreCar() {
        this.brand = Input.string("Marca del coche: ");
        this.brand = Input.string("Modelo del coche: ");
    }

    public ScoreCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public int getSpeedmether() {
        return speedmether;
    }

    public void setSpeedmether(int speedmether) {
        this.speedmether = speedmether;
    }

    public double getDistance() {
        return distance;
    }

    public void speedUp(){
        if(this.speedmether < ScoreCar.MAX_VELOCITY){
            speedmether+= 10;
        }
    }

    public void slowDown(){
        if(this.speedmether > 0){
            speedmether -= 10;
        }
    }

    public void calculateVelocity(){
        int isAccelerating = Util.getRandomNumberInRange(1, 3);
        if(isAccelerating  != 2){
            speedUp();
        } else {
            slowDown();
        }
        updateDistance();
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void updateDistance(){
        distance += speedmether * 16.67;
    }

    @Override
    public String toString() {
        return "ScoreCar{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", garageName='" + garageName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        //ScoreCar c = new ScoreCar();
        //System.out.println(c);

        ScoreCar c1 = new ScoreCar("Seat", "Ibiza");
        System.out.println(c1);

        ScoreCar c2 = new ScoreCar("Citroen", "C3");
        System.out.println(c2);

        //Acelera y frena
        for (int i = 0; i < 12; i++){
            c1.calculateVelocity();
            c2.calculateVelocity();
        }
        System.out.println("Velocidad final tras 12 min: " + c1.getSpeedmether());
        System.out.println("VDistancia: " + c1.getDistance());
        System.out.println("Velocidad final tras 12 min: " + c2.getSpeedmether());
        System.out.println("VDistancia: " + c2.getDistance());

        System.out.println(c1.compareTo(c2));
    }

    @Override
    public int compareTo(ScoreCar o) {
        if(this.getDistance() > o.getDistance()){
            return 1;
        } else if (this.getDistance() < o.getDistance()){
            return -1;
        } else {
            return 0;
        }
    }
}
