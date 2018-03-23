package com.codeclan.jurassicpark.db.models;

public abstract class Dinosaur {

    private int id;
    private SpeciesType species;
    private String name;
    private int age;
    private int danger;
    private Paddock paddock;
    private boolean secure;

    public Dinosaur() {
    }

    public Dinosaur(SpeciesType species, String name, int age, int danger, Paddock paddock) {
        this.species = species;
        this.name = name;
        this.age = age;
        this.danger = danger;
        this.paddock = paddock;
        this.secure = true;     // initialises as secure
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDanger() {
        return danger;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
