package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Paddock;

import java.util.List;

public class DBPaddock {

    public static int getDinoCount(Paddock paddock){
        List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
        return dinosaurs.size();
    }

}
