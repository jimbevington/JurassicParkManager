package com.codeclan.jurassicpark.db.models;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class runPark {

    //timed method for increasing hunger
    Runnable hungerIncrease = new Runnable() {
        public int increaseHunger() {
            hunger += 1;
            return hunger;
        }
    }
    ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
    exec.scheduleAtFixedRate(hungerIncrease , 0, 1, TimeUnit.MINUTES);
}
