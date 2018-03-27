package com.codeclan.jurassicpark.db.models;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

    public Test() {
    }

    public static void main(String[] args) {
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
                for(Carnivore carnivore : carnivores){
                    carnivore.increaseHunger();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
