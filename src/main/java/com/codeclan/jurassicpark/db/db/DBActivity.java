package com.codeclan.jurassicpark.db.db;

import java.util.Random;

public class DBActivity {

    public static void generateActivity(){

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
        Random rand = new Random();
        int choice = rand.nextInt(2);

        if (choice == 1){
            generateActivity();
        }
    }

}
