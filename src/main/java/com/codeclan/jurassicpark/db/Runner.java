package com.codeclan.jurassicpark.db;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.DBPaddock;
import com.codeclan.jurassicpark.db.db.DBVisitor;
import com.codeclan.jurassicpark.db.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Runner {

    public static void main(String[] args) {


        Paddock nurseryPaddock = new Paddock("Nursery", 50);
        DBHelper.saveOrUpdate(nurseryPaddock);
        Paddock containmentPaddock = new Paddock("Containment", 100);
        DBHelper.saveOrUpdate(containmentPaddock);
        Paddock paddock1 = new Paddock("Green Gully", 10);
        DBHelper.saveOrUpdate(paddock1);
        Paddock paddock2 = new Paddock("Arid Desert", 10);
        DBHelper.saveOrUpdate(paddock2);
        Paddock paddock3 = new Paddock("Misty Mountains", 10);
        DBHelper.saveOrUpdate(paddock3);
        Paddock paddock4 = new Paddock("The Great Plains", 10);
        DBHelper.saveOrUpdate(paddock4);

        Carnivore carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70, paddock1);
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

//        TESTS


//        test can get List of Objects
        List<Herbivore> foundHerbivores = DBHelper.getAll(Herbivore.class);
        List<Carnivore> foundCarnivores = DBHelper.getAll(Carnivore.class);
//        test can get all Dinosaurs
        List<Dinosaur> foundDinosaurs = DBHelper.getAll(Dinosaur.class);
        List<Paddock> foundPaddocks = DBHelper.getAll(Paddock.class);
        List<Visitor> foundVisitors = DBHelper.getAll(Visitor.class);

//        test can get Individual Objects
        Herbivore foundHerbivore = DBHelper.find(Herbivore.class, herbivore2.getId());
        Carnivore foundCarnivore = DBHelper.find(Carnivore.class, carnivore3.getId());
//        test can use generic Dinosaur class
        Dinosaur foundDinosaur = DBHelper.find(Dinosaur.class, carnivore1.getId());
        Paddock foundPaddock2 = DBHelper.find(Paddock.class, paddock2.getId());
        Paddock foundPaddock4 = DBHelper.find(Paddock.class, paddock4.getId());

        List<Dinosaur> paddock2Dinos = DBHelper.getPaddocksDinosaurs(foundPaddock2);
        List<Dinosaur> paddock4Dinos = DBHelper.getPaddocksDinosaurs(foundPaddock4);


//        testing getAvailablePaddocks
//        empty paddocks shown for all
        Paddock emptyPaddock = new Paddock("Empty", 10);   // will always be shown
        DBHelper.saveOrUpdate(emptyPaddock);
//        paddock can't be shown if full
        Paddock noCapacityPaddock = new Paddock("No Space", 0);   // will never be shown as has no capacity
        DBHelper.saveOrUpdate(noCapacityPaddock);
//        should return Paddock 3 and 4 and emptyPaddock
//        List<Paddock> paddocksAvailableForHerbivores = DBDinosaur.getAvailablePaddocks(foundHerbivore);
//        should test Carnivores allowed only with other Carnivores
        Paddock testCarnPaddock = new Paddock("testCarnivorePaddock", 80);  // for testing trex
        DBHelper.saveOrUpdate(testCarnPaddock);
        Carnivore newTrex = new Carnivore(SpeciesType.TREX, "Reginald", 6, 90, testCarnPaddock);
        DBHelper.saveOrUpdate(newTrex);
        List<Paddock> paddocksAvailableForTrex = DBDinosaur.getAvailablePaddocks(newTrex);


//        test the getAvailableDinosaurs

        List<Dinosaur> noDinosaursAvailable = DBPaddock.getAvailableDinosaurs(noCapacityPaddock);
//        should show the dinos that are in paddock 3 currently
        List<Dinosaur> herbivoresAvailable = DBPaddock.getAvailableDinosaurs(foundPaddock4);
//        should show Fluffy and Trevor T REX', not Fluffy
        List<Dinosaur> carnivoresAvailable = DBPaddock.getAvailableDinosaurs(testCarnPaddock);


//        test ADD VISITOR TO PADDOCK
        DBPaddock.addVisitorToPaddock(visitor1, paddock2);
        DBPaddock.addVisitorToPaddock(visitor2, paddock2);
        Paddock paddockWithVisitor = DBHelper.find(Paddock.class, foundPaddock2.getId());
        Visitor visitorWithPaddock = DBHelper.find(Visitor.class, visitor1.getId());

        List<Visitor> paddock2Visitors = DBHelper.getPaddocksVisitors(paddock2);
        int paddock2VisitorCount = DBPaddock.getVisitorCount(paddock2);

        int totalVisitors = DBHelper.totalVisitors();

        Paddock visitor3Paddock = visitor3.getPaddock();


        HashMap<String, List<Visitor>> sortedVisitors = DBVisitor.sortVisitors();
        List<Visitor> inParkVisitors = sortedVisitors.get("inPark");
        List<Visitor> notInParkVisitors = sortedVisitors.get("notInPark");

        List<Paddock> parkPaddocks = DBPaddock.getParkPaddocks();

        Carnivore babyCarnivore = new Carnivore(SpeciesType.TREX, "Junior", 1, 10, nurseryPaddock);
        DBHelper.saveOrUpdate(babyCarnivore);

        Carnivore containedCarnivore = new Carnivore(SpeciesType.TREX, "Alan", 4, 50, containmentPaddock);
        DBHelper.saveOrUpdate(containedCarnivore);
        HashMap<String, List<Dinosaur>> sortedDinos = DBDinosaur.sortDinosaurs();
        List<Dinosaur> inNursery = sortedDinos.get("inNursery");
        List<Dinosaur> inPark = sortedDinos.get("inPark");
        List<Dinosaur> inContainment = sortedDinos.get("inContainment");


//        test REMOVE VISITOR from PADDOCK
        DBPaddock.removeVisitorFromPaddock(visitor1, paddock2);
        DBPaddock.removeVisitorFromPaddock(visitor4, paddock2); // testing doesn't crash if removing non-existent visitor
        Paddock paddockRemovedVisitor = DBHelper.find(Paddock.class, paddock2.getId());
        Visitor removedVisitor = DBHelper.find(Visitor.class, visitor1.getId());

//        test LOCKDOWN PADDOCK
        DBPaddock.addVisitorToPaddock(visitor4, paddock2);
        Paddock updatedVisitorPaddock = DBHelper.find(Paddock.class, paddock2.getId());
        DBPaddock.lockDownPaddock(paddock2);
        Paddock noVisitorPaddock = DBHelper.find(Paddock.class, paddock2.getId());
        Visitor removedVisitor2 = DBHelper.find(Visitor.class, visitor4.getId());

//        test RAMPAGING DINOS
        DBPaddock.addVisitorToPaddock(visitor3, paddock1);
        DBPaddock.addVisitorToPaddock(visitor4, paddock1);
        DBDinosaur.rampage(carnivore1);
        Dinosaur rampagingDino = DBHelper.find(Dinosaur.class, carnivore1.getId());
        Paddock unsecurePaddock = DBHelper.find(Paddock.class, paddock1.getId());
        List<Visitor> visitorsPostRampage = DBHelper.getAll(Visitor.class);

//        test DINO ALERTS
        Carnivore hungryCarnivore = new Carnivore(SpeciesType.TREX, "Geraldine", 4, 60, paddock1);
        hungryCarnivore.setHunger(8);
        DBHelper.saveOrUpdate(hungryCarnivore);

        List<Dinosaur> dinoAlerts = DBDinosaur.getDinoAlerts();

//        test GET DINO ALERT TYPE as INSTANCE of DBDINOSAUR for use inside VIEWS
        DBDinosaur dbDinosaur = new DBDinosaur();

        String escaped = dbDinosaur.getDinoAlertType(rampagingDino);
        String hungry = dbDinosaur.getDinoAlertType(hungryCarnivore);


        //TEST delete Dinosaur
        Carnivore carnivore8 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Delta", 10, 85, paddock1);
        DBHelper.saveOrUpdate(carnivore8);

        Carnivore carnivore9 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Charlie", 10, 85, paddock1);
        DBHelper.saveOrUpdate(carnivore9);

        DBHelper.delete(carnivore9);


        //TEST Status Messages
        List<String> messages = DBHelper.getStatusMsgs();


//        test LockDown closes Paddock
        Paddock paddockToLockdown = DBHelper.find(Paddock.class, paddock4.getId());
        DBPaddock.lockDownPaddock(paddockToLockdown);
        Paddock lockedDownPaddock = DBHelper.find(Paddock.class, paddock4.getId());
        boolean paddockOpenIsFalse = lockedDownPaddock.isOpen();
        String paddockClosed = DBPaddock.checkPaddockOpen(lockedDownPaddock);
        DBPaddock.openPaddock(lockedDownPaddock);
        Paddock openedPaddock = DBHelper.find(Paddock.class, paddock4.getId());
        boolean paddockOpenIsTrue = openedPaddock.isOpen();
        String paddockOpen = DBPaddock.checkPaddockOpen(openedPaddock);
    }





}


