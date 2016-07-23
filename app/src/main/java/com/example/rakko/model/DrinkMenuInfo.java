package com.example.rakko.model;

import java.io.Serializable;

/**
 * Created by rakko on 2016/07/18.
 */
public class DrinkMenuInfo implements Serializable{
    private String name;
    private int cost;

    public DrinkMenuInfo(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
