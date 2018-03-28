package com.codeclan.jurassicpark.db.models;

public enum AlertType {
    NONE(""),
    HUNGRY("Hungry Dino"),
    SICK("Sick Dino"),
    ESCAPE("ESCAPE");

    private String alert;

    AlertType(String alert){
        this.alert = alert;
    }

    public String getAlert(){
        return this.alert;
    }
}
