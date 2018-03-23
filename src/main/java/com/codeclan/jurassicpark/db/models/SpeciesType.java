package com.codeclan.jurassicpark.db.models;

public enum SpeciesType {
    VELOCIRAPTOR("Velociraptor"),
    TREX("T-Rex"),
    TRICERATOPS("Triceratops"),
    BRACHIOSAURUS("Brachiosaurus");

    private String species;

    SpeciesType(String species){
        this.species = species;
    }

    public String getSpecies(){
        return this.species;
    }


}
