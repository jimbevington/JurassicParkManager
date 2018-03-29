package com.codeclan.jurassicpark.db.db;

import java.util.Random;

public class DBActivity {

    public static void generateActivity(){

//        make a decision to generate Activity
//        choose an activity: move Visitors, get Hungry, makeRampage
//        carry it out.

        Random rand = new Random();
        int choice = rand.nextInt(9);

        if (choice > 5) {
            DBDinosaur.makeRampage();
        } else  {
            DBDinosaur.makeHungry();
            DBVisitor.moveVisitors();
        }

    }

    public static void makeActivityDecision(){

    }

}
