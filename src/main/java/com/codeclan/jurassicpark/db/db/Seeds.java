package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.*;


public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Carnivore.class);
        DBHelper.deleteAll(Herbivore.class);
        DBHelper.deleteAll(Paddock.class);
        DBHelper.deleteAll(Visitor.class);
        DBHelper.deleteAll(Park.class);


        Park park = new Park("Jurassic Park");
        DBHelper.saveOrUpdate(park);


//        PADDOCKS

//        Nursery Paddock - always has ID 1
        Paddock nurseryPaddock= new Paddock("Nursery", 50, park);
        DBHelper.saveOrUpdate(nurseryPaddock);

//        Containment Paddock - always has ID 2
        Paddock containmentPaddock = new Paddock("Containment", 100, park);
        DBHelper.saveOrUpdate(containmentPaddock);

        Paddock paddock1 = new Paddock("Green Gully", 10, park);
        DBHelper.saveOrUpdate(paddock1);
        Paddock paddock2 = new Paddock("Arid Desert", 10, park);
        DBHelper.saveOrUpdate(paddock2);
        Paddock paddock3 = new Paddock("Misty Mountains", 10, park);
        DBHelper.saveOrUpdate(paddock3);
        Paddock paddock4 = new Paddock("The Great Plains", 10, park);
        DBHelper.saveOrUpdate(paddock4);
        Paddock paddock5 = new Paddock("Treachorous Tundra", 8, park);
        DBHelper.saveOrUpdate(paddock5);

        Carnivore babyCarnivore = new Carnivore(SpeciesType.TREX, "Junior", 1, 10, nurseryPaddock);
        DBHelper.saveOrUpdate(babyCarnivore);

        Carnivore carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70, paddock1);
        DBHelper.saveOrUpdate(carnivore1);
        carnivore1.setHunger(6);
        DBHelper.saveOrUpdate(carnivore1);

        Carnivore carnivore2 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Blue", 4, 50, paddock2);
        DBHelper.saveOrUpdate(carnivore2);

        Carnivore carnivore3 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Athena", 10, 90, paddock2);
        DBHelper.saveOrUpdate(carnivore3);

        Carnivore carnivore4 = new Carnivore(SpeciesType.TREX, "Trevor", 16, 70, paddock1);
        DBHelper.saveOrUpdate(carnivore4);


        Herbivore herbivore1 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Betty", 20, 50, paddock3);
        DBHelper.saveOrUpdate(herbivore1);

        Herbivore herbivore2 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Barbara", 18, 50, paddock4);
        DBHelper.saveOrUpdate(herbivore2);

        Herbivore herbivore3 = new Herbivore(SpeciesType.TRICERATOPS, "Terry", 37, 80, paddock3);
        DBHelper.saveOrUpdate(herbivore3);

        Herbivore herbivore4 = new Herbivore(SpeciesType.TRICERATOPS, "Thomas", 42, 80, paddock4);
        DBHelper.saveOrUpdate(herbivore4);

        Visitor visitor1 = new Visitor("Alan Grant");
        DBHelper.saveOrUpdate(visitor1);
        Visitor visitor2 = new Visitor("Ellie Satler");
        DBHelper.saveOrUpdate(visitor2);
        Visitor visitor3 = new Visitor("Ian Malcolm");
        DBHelper.saveOrUpdate(visitor3);
        Visitor visitor4 = new Visitor("Donald Gennaro");
        DBHelper.saveOrUpdate(visitor4);
        Visitor visitor5 = new Visitor("John Hammond");
        DBHelper.saveOrUpdate(visitor5);
        Visitor visitor6 = new Visitor("Lex Murphy");
        DBHelper.saveOrUpdate(visitor6);
        Visitor visitor7 = new Visitor("Tim Murphy");
        DBHelper.saveOrUpdate(visitor7);
        Visitor visitor8 = new Visitor("Dr Henry Wu");
        DBHelper.saveOrUpdate(visitor8);
        Visitor visitor9 = new Visitor("Ray Arnold");
        DBHelper.saveOrUpdate(visitor9);
        Visitor visitor10 = new Visitor("Mr D.N.A");
        DBHelper.saveOrUpdate(visitor10);
        Visitor visitor11 = new Visitor("Dennis Nedry");
        DBHelper.saveOrUpdate(visitor11);


        DBPaddock.addVisitorToPaddock(visitor1, paddock2);
        DBPaddock.addVisitorToPaddock(visitor2, paddock2);

        DBDinosaur.rampage(carnivore1);
        carnivore2.setHunger(9);
        DBHelper.saveOrUpdate(carnivore2);
    }
}
