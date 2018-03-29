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
        int num = rand.nextInt(6) + 1;
        for (int i = 0; i < num; i++) {
            List<Paddock> paddocks = DBPaddock.getParkPaddocks();
            Collections.shuffle(paddocks);
            Paddock paddock = paddocks.get(0);

            List<Visitor> paddocksVisitors = DBHelper.getPaddocksVisitors(paddock);
            for (Visitor visitor : paddocksVisitors){
                DBPaddock.removeVisitorFromPaddock(visitor, paddock);
            }

            List<Visitor> visitors = DBHelper.getAll(Visitor.class);
            Collections.shuffle(visitors);
            Visitor visitor = visitors.get(0);
            if (paddock.getAlert() == AlertType.NONE) {
                DBPaddock.addVisitorToPaddock(visitor, paddock);
                DBHelper.saveOrUpdate(visitor);
                DBHelper.saveOrUpdate(paddock);
            }
        }
    }
}
