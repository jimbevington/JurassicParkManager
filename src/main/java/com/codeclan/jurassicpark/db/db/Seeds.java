package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.SpeciesType;

public class Seeds {

    public static void seedData{
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);

        Paddock paddock1 = new Paddock("Green Gully", 10);
        DBHelper.saveOrUpdate(paddock1);

        Paddock paddock2 = new Paddock("Arid Desert", 10);
        DBHelper.saveOrUpdate(paddock2);

        Paddock paddock3 = new Paddock("Misty Mountains", 10);
        DBHelper.saveOrUpdate(paddock3);

        Paddock paddock4 = new Paddock("The Great Plains", 10);
        DBHelper.saveOrUpdate(paddock4);


        Carnivore carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70);
        DBHelper.saveOrUpdate(carnivore1);

        Carnivore carnivore2 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Blue", 4, 50);
        DBHelper.saveOrUpdate(carnivore2);

        Carnivore carnivore3 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Athena", 10, 90);
        DBHelper.saveOrUpdate(carnivore3);

        Carnivore carnivore4 = new Carnivore(SpeciesType.TREX, "Trevor", 16, 70);
        DBHelper.saveOrUpdate(carnivore4);


        Herbivore herbivore1 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Betty", 20, 50);
        DBHelper.saveOrUpdate(herbivore1);

        Herbivore herbivore2 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Barbara", 18, 50);
        DBHelper.saveOrUpdate(herbivore2);

        Herbivore herbivore3 = new Herbivore(SpeciesType.TRICERATOPS, "Terry", 37, 80);
        DBHelper.saveOrUpdate(herbivore3);

        Herbivore herbivore4 = new Herbivore(SpeciesType.TRICERATOPS, "Thomas", 42, 80);
        DBHelper.saveOrUpdate(herbivore4);
    }
}
