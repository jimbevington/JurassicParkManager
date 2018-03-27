package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBVisitor {

    public static HashMap<String, List<Visitor>> sortVisitors(){

        List<Visitor> allVisitors = DBHelper.getAll(Visitor.class);
        List<Visitor> notInPark = new ArrayList<>();
        List<Visitor> inPark = new ArrayList<>();

        HashMap<String, List<Visitor>> visitors = new HashMap<>();

        for (Visitor visitor : allVisitors){
            if (visitor.getPaddock() == null){
                notInPark.add(visitor);
            } else {
                inPark.add(visitor);
            }
        }

        visitors.put("notInPark", notInPark);
        visitors.put("inPark", inPark);

        return visitors;

    }


}
