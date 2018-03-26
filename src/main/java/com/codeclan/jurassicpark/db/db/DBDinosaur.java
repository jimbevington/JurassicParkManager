package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;

import java.util.ArrayList;
import java.util.List;

public class DBDinosaur {

//    need to remove Dinosaurs current Paddock

    public static List<Paddock> getAvailablePaddocks(Dinosaur dinosaur){

        List<Paddock> allPaddocks = DBHelper.getAll(Paddock.class);
        List<Paddock> available = new ArrayList<>();

        for (Paddock paddock : allPaddocks){
            if (paddockEmpty(paddock)){
                available.add(paddock);
            } else if (carnivoreTest(paddock, dinosaur)){
                available.add(paddock);
            } else if(herbivoreTest(paddock, dinosaur)){
                available.add(paddock);
            }
        }

        return available;
    }

    private static boolean carnivoreTest(Paddock paddock, Dinosaur dinosaur) {

        boolean result = false;

        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        Dinosaur firstDino = dinosaurs.get(0);

        boolean spaceInPaddock = dinosaurs.size() < paddock.getCapacity();

        boolean sameSpecies = dinosaur.getSpecies() == firstDino.getSpecies();

        if (spaceInPaddock && sameSpecies){
            result = true;
        }

        return result;

    }

    private static boolean herbivoreTest(Paddock paddock, Dinosaur dinosaur) {

        boolean result = false;

        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        Dinosaur firstDino = dinosaurs.get(0);

        boolean spaceInPaddock = dinosaurs.size() < paddock.getCapacity();

        boolean herbivorePaddock = firstDino instanceof Herbivore;

        if (spaceInPaddock && herbivorePaddock){
            result = true;
        }

        return result;

    }

    private static boolean paddockEmpty(Paddock paddock) {
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
//        return True if no Dinosaurs
        return dinosaurs.size() == 0;
    }


}
