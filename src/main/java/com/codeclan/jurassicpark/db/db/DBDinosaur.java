package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.*;
import org.apache.commons.lang.ArrayUtils;

import javax.smartcardio.CardNotPresentException;
import java.util.*;

public class DBDinosaur {

    public static HashMap<String, List<Dinosaur>> sortDinosaurs(){

        HashMap<String, List<Dinosaur>> sortedDinos = new HashMap<>();

        List<Dinosaur> all = DBHelper.getAll(Dinosaur.class);
        List<Dinosaur> inPark = new ArrayList<>();
        List<Dinosaur> inNursery = new ArrayList<>();
        List<Dinosaur> inContainment = new ArrayList<>();

        for (Dinosaur dinosaur : all){
            if (dinosaur.getPaddock().getId() == 1){
                inNursery.add(dinosaur);
            }  else if (dinosaur.getPaddock().getId() == 2) {
                inContainment.add(dinosaur);
            } else {
                inPark.add(dinosaur);
            }
        }

        sortedDinos.put("all", all);
        sortedDinos.put("inPark", inPark);
        sortedDinos.put("inNursery", inNursery);
        sortedDinos.put("inContainment", inContainment);

        return sortedDinos;
    }


    public static List<Paddock> getAvailablePaddocks(Dinosaur dinosaur){

        List<Paddock> allPaddocks = DBHelper.getAll(Paddock.class);

        int currentPaddockId = dinosaur.getPaddock().getId();
        Paddock currentPaddock = DBHelper.find(Paddock.class, currentPaddockId);
        allPaddocks.remove(currentPaddock);

//        make Nursery Unavailable
        Paddock nurseryPaddock = DBHelper.find(Paddock.class, 1);
        allPaddocks.remove(nurseryPaddock);

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

    public static boolean speciesTest(Dinosaur dinosaur){
        boolean result = false;
        if (dinosaur.getSpecies() == SpeciesType.TREX) {
            result = true;
        } else {
            if(dinosaur.getSpecies() == SpeciesType.VELOCIRAPTOR){
                result = true;
            } else {
            result = false;
            }
        }
        return result;
    }

    public static void rampage(Dinosaur dinosaur){
        dinosaur.setSecure(false);
        DBHelper.saveOrUpdate(dinosaur);
        Paddock paddock = dinosaur.getPaddock();
        DBPaddock.checkAlert(paddock);

        List<Visitor> visitors = DBHelper.getPaddocksVisitors(paddock);
        if (visitors.size() > 1){
            Collections.shuffle(visitors);
            Visitor visitor = visitors.get(0);
            DBPaddock.removeVisitorFromPaddock(visitor, paddock);
            DBHelper.delete(visitor);
        }
    }
  
    public static void capture(Dinosaur dinosaur){
        dinosaur.setSecure(true);
        DBHelper.saveOrUpdate(dinosaur);
        Paddock paddock = dinosaur.getPaddock();
        DBPaddock.checkAlert(paddock);
    }

    public static List<Dinosaur> getDinoAlerts(){

        List<Dinosaur> all = DBHelper.getAll(Dinosaur.class);
        List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
        List<Dinosaur> alerts = new ArrayList<>();

//        add Unsecured Dinosaurs
        for (Dinosaur dinosaur : all){
            if (!dinosaur.isSecure()){
                alerts.add(dinosaur);
            }
        }

        for (Carnivore carnivore : carnivores){
            if (carnivore.getHunger() > 7 && !alerts.contains(carnivore)){
                alerts.add(carnivore);
            }
        }

        return alerts;
    }

    public static String getDinoAlertType(Dinosaur dinosaur){

        String alertType = null;

        if (!dinosaur.isSecure()){
            alertType = "ESCAPED!";
        } else {
            Carnivore carnivore = (Carnivore) dinosaur;
            if (carnivore.getHunger() > 7){
                alertType = "Hungry";
            }
        }

        return alertType;

    }

    public static boolean isHungry(Dinosaur dinosaur){

        boolean result = false;

        if (dinosaur instanceof Carnivore){
            Carnivore carnivore = (Carnivore) dinosaur;
            if (carnivore.getHunger() > 7){
                result = true;
            }
        }

        return result;

    }

}

