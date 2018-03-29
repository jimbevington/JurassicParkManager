package com.codeclan.jurassicpark.db.db;

import java.util.Random;

public class DBActivity {

    public static void generateActivity(){

//        make a decision to generate Activity
//        choose an activity: move Visitors, get Hungry, makeRampage
//        carry it out.

        Random rand = new Random();
        int choice = rand.nextInt(9);

        // least likely is Rampage
        // Make Hungry and Move Visitors equally likely
        if (choice > 6) {
            DBDinosaur.makeRampage();
        } else if (choice % 2 == 0) {
            DBDinosaur.makeHungry();
        } else {
            DBVisitor.moveVisitors();
        }

        System.out.println(choice);

    }

    public static void makeActivityDecision(){

    }

}
