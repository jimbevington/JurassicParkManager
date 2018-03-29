package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.AlertType;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.Visitor;

import java.util.*;

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

        visitors.put("all", allVisitors);
        visitors.put("notInPark", notInPark);
        visitors.put("inPark", inPark);

        return visitors;

    }

    public static void moveVisitors(){

        Random rand = new Random();
        int num = rand.nextInt(4);

        List<Paddock> paddocks = DBPaddock.getParkPaddocks();
        Collections.shuffle(paddocks);
        List<Visitor> visitors = DBHelper.getAll(Visitor.class);
        Collections.shuffle(visitors);

        for (int i = 0; i <= num; i++) {

            Visitor visitor = visitors.get(i);
            Paddock paddock = paddocks.get(i);

            if (visitor.getPaddock() != null){
                DBPaddock.removeVisitorFromPaddock(visitor, visitor.getPaddock());
            }

            DBPaddock.addVisitorToPaddock(visitor, paddock);
        }
    }
}
