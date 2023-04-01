package com.kenzie.app;

public class Player {
    private String name;
    private int points;

    public Player(){
        this.name = "Player";
        this.points = 0;
    }

    public Player(String name, int points){
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
