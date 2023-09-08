package com.campusdual.racecontrol.util;

import com.campusdual.racecontrol.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Util {

    public static Random random = new Random();

    //Genera un aleatorio entre "true" y "false"
    public static boolean randomBoolean(){
        boolean state;
        Random rd = new Random();
        state = rd.nextBoolean();
        return state;
    }
    //Genera un aleatorio entre los parametros pasados (ambos incluidos)
    public static int randomGenerator(int from, int to) {
        boolean correct = false;
        int randomNumber = 0;
        if(from > to){
            int aux = from;
            from = to;
            to = aux;
            from -=1 ;
        } else if (from < to){
            from -= 1;
        }
        do {
            randomNumber = (int)(Math.random()*(to-from+1)+from);
            if ((from + 1) <= randomNumber && to >= randomNumber){
                correct = true;
            }
        } while(!correct);
        return randomNumber;
    }

    public static int getRandomNumberInRange(int max, int min){
        max++;
        return random.nextInt((max-min) + min);
    }



}
