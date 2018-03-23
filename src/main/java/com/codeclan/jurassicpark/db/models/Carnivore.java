package com.codeclan.jurassicpark.db.models;

public class Carnivore extends Dinosaur {

    private int hunger;

    public Carnivore() {
    }

    public Carnivore(SpeciesType species, String name, int age, int danger, Paddock paddock, int hunger) {
        super(species, name, age, danger, paddock);
        this.hunger = hunger;
    }

    public void getFed(){
//        feeding Dinosaur resets Hunger to 0
        this.hunger = 0;
    }
}
