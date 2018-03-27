package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DBVisitor {

    public static List<Visitor> inPark(){
        List<Visitor> allVisitors = DBHelper.getAll(Visitor.class);
        List<Visitor> inPark = new ArrayList<>();

        for (Visitor visitor : allVisitors){
            if (visitor.getPaddock() != null){
                inPark.add(visitor);
            }
        }

        return inPark;

    }
}
