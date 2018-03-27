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
    private AlertType alert;
    private Set<Dinosaur> dinosaurs;
    private Set<Visitor> visitors;

    public Paddock() {
    }

    public Paddock(String name, int capacity, Park park) {
        this.name = name;
        this.capacity = capacity;
        this.park = park;
        this.open = true;
        this.alert = AlertType.NONE;
        this.visitors = new HashSet<>();
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

    @Column(name="alert")
    public AlertType getAlert() {
        return alert;
    }

    public void setAlert(AlertType alert) {
        this.alert = alert;
    }

    public String getAlertMessage(){
        return this.alert.getAlert();
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
    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Visitor visitor){
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.visitors.remove(visitor);
    }

    public int dinoCount(){
        return this.dinosaurs.size();
    }

    public int visitorCount(){
        return this.visitors.size();
    }
}
