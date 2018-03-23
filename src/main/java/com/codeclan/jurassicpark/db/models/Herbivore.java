package com.codeclan.jurassicpark.db.models;

public class Herbivore extends Dinosaur{

    public Herbivore() {
    }

    public Herbivore(SpeciesType species, String name, int age, int danger) {
        super(species, name, age, danger);
    }

}
