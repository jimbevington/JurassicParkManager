package com.codeclan.jurassicpark.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="paddocks")
public class Paddock {

    private int id;
    private String name;
    private int capacity;
    private Park park;
    private boolean open;
    private Set<Dinosaur> dinosaurs;
    private List<Visitor> visitors;

    public Paddock() {
    }

    public Paddock(String name, int capacity, Park park) {
        this.name = name;
        this.capacity = capacity;
        this.park = park;
        this.open = true;
        this.visitors = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @ManyToOne
    @JoinColumn(name="park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    @Column(name="open")
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.EAGER)
    public Set<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(Set<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    @Override
    public boolean equals(Object object){
        Paddock paddock = (Paddock) object;
        return this.id == paddock.getId();
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.EAGER)
    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Visitor visitor){
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.visitors.remove(visitor);
    }
}
