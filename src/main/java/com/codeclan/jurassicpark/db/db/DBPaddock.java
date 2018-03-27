package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DBPaddock {

    private static Session session;
    private static Transaction transaction;

    public static String getPaddockDinoType(Paddock paddock){
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);

        String result = "empty";

        if (!paddockEmpty(paddock)){

            Dinosaur firstDino = dinosaurs.get(0);

            if (firstDino instanceof Carnivore){
                result = "carnivore";
            } else {
                result = "herbivore";
            }

        }

        return result;
    }

    private static boolean paddockEmpty(Paddock paddock) {

        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        boolean result = false;

        if (dinosaurs.size() == 0){
            result = true;
        }

        return result;
    }

    public static List<Dinosaur> getAvailableDinosaurs(Paddock paddock){

//        return me a list of Dinosaurs
        List<Dinosaur> availableDinosaurs = new ArrayList<>();

        List<Dinosaur> allDinosaurs = DBHelper.getAll(Dinosaur.class);
        List<Dinosaur> currentDinosaurs = DBHelper.getPaddocksDinosaurs(paddock);

//      Skip over if the Paddock is empty
        if (paddockHasSpace(paddock)){

//            if the first Dino is a Herbivore, set availableDinosaurs to: all the Herbivores
            if (herbivoreTest(paddock)) {
                availableDinosaurs = DBHelper.getAll(Herbivore.class);
            }
//            otherwise, the Paddock must contain Carnivores
//            ... so get the first Dino from the Paddock and add all Dinos of same species to available Dinosaurs
            else {
                Dinosaur firstDino = currentDinosaurs.get(0);
                for (Dinosaur dinosaur : allDinosaurs){
                    if (firstDino.getSpecies() == dinosaur.getSpecies()){
                        availableDinosaurs.add(dinosaur);
                    }
                }
            }
        }

//        remove all Dinosaurs currently in the Paddock from the Available Dinosaurs list
        for (Dinosaur dinosaur : currentDinosaurs){
            availableDinosaurs.remove(dinosaur);
        }

        return availableDinosaurs;


    }

    private static boolean herbivoreTest(Paddock paddock) {
        List<Dinosaur> currentDinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        Dinosaur firstDino = currentDinosaurs.get(0);
        return firstDino instanceof Herbivore;
    }

    public static boolean paddockHasSpace(Paddock paddock) {
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        return dinosaurs.size() < paddock.getCapacity();

    }

    public static void addVisitorToPaddock(Visitor visitor, Paddock paddock){
        paddock.addVisitor(visitor);
        visitor.setPaddock(paddock);
        DBHelper.saveOrUpdate(visitor);
        DBHelper.saveOrUpdate(paddock);
    }

    public static int getVisitorCount(Paddock paddock){
        List<Visitor> visitors = DBHelper.getPaddocksVisitors(paddock);
        return visitors.size();
    }

}
