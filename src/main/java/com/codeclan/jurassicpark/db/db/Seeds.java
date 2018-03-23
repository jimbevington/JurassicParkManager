package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import sun.security.pkcs11.Secmod;

public class Seeds {

    public static void seedData{
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);

        Paddock paddock1 = new Paddock("Green Gully", 10);
        DBHelper.saveOrUpdate(paddock1);

        Carnivore carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70, paddock1);
        DBHelper.saveOrUpdate(carnivore1);

        Carnivore carnivore2 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Blue", 4, 50, paddock1);
        DBHelper.saveOrUpdate(carnivore2);

        Carnivore carnivore3 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Athena", 10, 90, paddock1);
        DBHelper.saveOrUpdate(carnivore3);

        Carnivore carnivore4 = new Carnivore(SpeciesType.TREX, "Trevor", 16, 70, paddock1);
        DBHelper.saveOrUpdate(carnivore4);
    }
}
