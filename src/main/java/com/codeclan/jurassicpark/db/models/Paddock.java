package com.codeclan.jurassicpark.db.models;

import java.util.HashSet;
import java.util.Set;

public class Paddock {

    private int id;
    private String name;
    private int capacity;
    private boolean open;
    private Set<Dinosaur> dinosaurs;

    public Paddock() {
    }

    public Paddock(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.open = true;
        this.dinosaurs = new HashSet<Dinosaur>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Set<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(Set<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    public void addDino(Dinosaur dinosaur){
        this.dinosaurs.add(dinosaur);
    }

    public void removeDino(Dinosaur dinosaur){
        this.dinosaurs.remove(dinosaur);
    }
}
