package com.codeclan.jurassicpark.db.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dinosaur {

    private int id;
    private SpeciesType species;
    private String name;
    private int age;
    private int danger;
    private Paddock paddock;
    private int health;
    private boolean secure;

    public Dinosaur() {
    }

    public Dinosaur(SpeciesType species, String name, int age, int danger, Paddock paddock) {
        this.species = species;
        this.name = name;
        this.age = age;
        this.danger = danger;
        this.paddock = paddock;
        this.health = 100;
        this.secure = true;     // initialises as secure
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

    @Column(name="species")
    public SpeciesType getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesType species) {
        this.species = species;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name="danger")
    public int getDanger() {
        return danger;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paddock_id", nullable = false)
    public Paddock getPaddock() {
        return paddock;
    }

    public void setPaddock(Paddock paddock) {
        this.paddock = paddock;
    }

    @Column(name="secure")
    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
