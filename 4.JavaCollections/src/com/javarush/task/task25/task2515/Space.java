package com.javarush.task.task25.task2515;

import java.util.ArrayList;

/**
 * Created by Mukovozchik on 25.05.2017.
 */
public class Space {
    private int width;
    private int height;
    private SpaceShip ship;
    private ArrayList<Ufo> ufos;
    private ArrayList<Rocket> rockets;
    private ArrayList<Bomb> bombs;

    public static Space game;

    public static void main(String[] args){
    }

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
        this.ufos = new ArrayList<>();
        this.rockets = new ArrayList<>();
        this.bombs = new ArrayList<>();
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public ArrayList<Ufo> getUfos() {
        return ufos;
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void run(){};

    public void draw(){};

    public void sleep(int ms){};
}
