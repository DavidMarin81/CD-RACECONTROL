package com.campusdual.racecontrol;

import com.campusdual.racecontrol.util.Input;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private static final String TOURNAMENT01 = "Mushroom Cup";
    private static final String TOURNAMENT02 = "Flower Cup";
    private static final String TOURNAMENT03 = "Star Cup";
    private static final String TOURNAMENT04 = "Special Cup";

    public static List<Race> auxListRace = new ArrayList<>();

    private Long id;
    private String name;
    private List<Race> raceList = new ArrayList<>();

    public Tournament(Long id,String name) {
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

    public List<Race> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", raceList=" + raceList +
                '}';
    }

    public static void showMenuTournament(){
        int contador = 1;
        int option;
        auxListRace.clear();
        do {
            System.out.println("=======================================");
            System.out.println("==         SELECT A TOURNAMENT       ==");
            System.out.println("=======================================");
            for(Tournament t : RaceControl.tournamentList){
                System.out.println("\t" + contador++ + " -> " + t.getName());
            }
            System.out.println("\t0.- Exit");
            option = Input.integer("Select an option: ");
            switch (option) {
                case 0:
                    break;
                case 1:
                    Tournament.auxListRace = Race.selectRacesForTournament(TOURNAMENT01);
                    break;
                case 2:
                    Tournament.auxListRace = Race.selectRacesForTournament(TOURNAMENT02);
                    break;
                case 3:
                    Tournament.auxListRace = Race.selectRacesForTournament(TOURNAMENT03);
                    break;
                case 4:
                    Tournament.auxListRace = Race.selectRacesForTournament(TOURNAMENT04);
                    break;
                default:
                    System.out.println("Wrong option!!!");
                    break;
            }
            if(option != 0){
                Race.raceMenu();
                option = 0;
            }
        }while (option != 0);
    }
}
