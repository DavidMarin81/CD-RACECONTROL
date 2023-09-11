package com.campusdual.racecontrol;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

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
}
