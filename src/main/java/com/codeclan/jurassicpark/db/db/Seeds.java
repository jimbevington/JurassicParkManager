package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Herbivore;

public class Seeds {

    public static void seedData{
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);


    }
}
