package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class DBDinosaur {

//    need to remove Dinosaurs current Paddock

    public static List<Paddock> getAvailablePaddocks(Dinosaur dinosaur){

        List<Paddock> allPaddocks = DBHelper.getAll(Paddock.class);

        int currentPaddockId = dinosaur.getPaddock().getId();
        Paddock currentPaddock = DBHelper.find(Paddock.class, currentPaddockId);
        allPaddocks.remove(currentPaddock);

        List<Paddock> available = new ArrayList<>();

        for (Paddock paddock : allPaddocks) {
            if (paddockHasSpace(paddock)) {
                if (paddockEmpty(paddock)) {
                    available.add(paddock);
                } else if (carnivoreTest(paddock, dinosaur)) {
                    available.add(paddock);
                } else if (herbivoreTest(paddock, dinosaur)) {
                    available.add(paddock);
                }
            }
        }

        return available;
    }

    private static boolean paddockHasSpace(Paddock paddock) {
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        return dinosaurs.size() < paddock.getCapacity();

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

        boolean herbivorePaddock = dinosaur instanceof Herbivore && firstDino instanceof Herbivore;

        if (spaceInPaddock && herbivorePaddock){
            result = true;
        }

        return result;

    }

    private static boolean paddockEmpty(Paddock paddock) {
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
//        return True if no Dinosaurs

        boolean result = false;

        boolean spaceInPaddock = dinosaurs.size() < paddock.getCapacity();

        boolean noDinos = dinosaurs.size() == 0;

        if (spaceInPaddock && noDinos){
            result = true;
        }

        return result;
    }

    public static List<Dinosaur> listAll(){
        List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
        List<Herbivore> herbivores = DBHelper.getAll(Herbivore.class);

        List<Dinosaur> dinosaurs = new ArrayList<>();
        dinosaurs.addAll(carnivores);
        dinosaurs.addAll(herbivores);

        return dinosaurs;
    }


}
