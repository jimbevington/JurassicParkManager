package com.codeclan.jurassicpark.db.models;

public enum ParkStatus {
    HIDEYOKIDSHIDEYOWIFE("Hide yo kids, hide yo wife!"),
    CURRENTLYALLSERVERSAREOPERATING("Currently all servers are operating normally"),
    INVESTIGATINGCAR1OFFLINE("Investigating: Car 1 is currently offline"),
    WEATHERREPORTINCOMING("Updated Weather Report incoming. Please stand by"),
    MAGICWORD("Ah, ah, ah. You didn't say the magic word"),
    MALCOLM("Life finds a way"),
    DEATHSTAR("The Death Star is fully operational"),
    BOATAPPROACH("Boat 3 on approach to East Dock"),
    BOATDEPART("Boat 5 departing on schedule"),
    NOINCIDENT("We have been 63 days without incident!"),
    EOTM("Employee of the Month is: Alan from Dino Waste Disposal!"),
    CTLIPREC("Connected to LAN. IP Received"),
    FINDDENNIS("Find Nedry! Check the vending machines!"),
    DODGSON("Dodgson! Dodgson! We got Dodgson here!"),
    ALPHA("The Raptors have a new Alpha."),
    PREBOOKING("Prebooking for the Indominus Rex is now open."),
    DINOTRACKING("Investigating: Dinosaur tracker 7 is offline"),
    CONSTRUCTION("Construction update: Indominus Rex paddock being made taller."),
    CONSTRUCTION2("Construction update: Aquatic Paddock for Mosasaur on schedule."),
    ELECTRICFENCE("All fences running with no issues."),
    GYROSPHERES("All Gyrospheres are in the field."),
    WESTPLAINS("Another Pachy roaming outside his zone. Full sedated and ready for relocation."),
    HORNYTRIC("The Triceratops are going at it again."),
    ASSETDEV("Update from Genetics: Gene-splicing test successful."),
    OPSUPDATE("Scheduled System Maintenance: 0200-0400 30.04.18"),
    VISCEN("Visitor Centre Tour reaching capacity. Queuing system initiated."),
    PADUP("Paddock Update: Misty Mountains have experienced small rockfall."),
    NURSUP("Nursery Update: 2 Brachiosauruses were born today."),
    WEATHERALERT("Weather Alert: Tropical Storm system approaching from South."),
    HELIPAD("Coastguard Helicopter on approach. Ready Helipad."),;

    private String status;

    ParkStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
