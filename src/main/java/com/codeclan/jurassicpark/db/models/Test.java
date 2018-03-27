package com.codeclan.jurassicpark.db.models;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.DBVisitor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

    public Test() {
    }

    public static void main(String[] args) {
        final ScheduledExecutorService hungerIncrease = Executors.newSingleThreadScheduledExecutor();
        hungerIncrease.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
                for (Carnivore carnivore : carnivores) {
                    carnivore.increaseHunger();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        final ScheduledExecutorService rampagingDinos = Executors.newSingleThreadScheduledExecutor();
        rampagingDinos.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                List<Dinosaur> dinosaurs = DBDinosaur.listAll();
                Collections.shuffle(dinosaurs);
                Dinosaur dinosaur = dinosaurs.get(0);
                DBDinosaur.rampage(dinosaur);
            }
        }, 5, 15, TimeUnit.SECONDS);

        final ScheduledExecutorService wanderingVisitors = Executors.newSingleThreadScheduledExecutor();
        rampagingDinos.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                DBVisitor.moveVisitors();
            }
        }, 3, 3, TimeUnit.SECONDS);
    }
}
