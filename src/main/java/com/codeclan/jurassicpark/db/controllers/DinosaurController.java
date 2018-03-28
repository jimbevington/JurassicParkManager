package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static spark.Spark.get;
import static spark.Spark.post;

public class DinosaurController {

    public DinosaurController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            HashMap<String, List<Dinosaur>> sortedDinos = DBDinosaur.sortDinosaurs();
            model.put("inPark", sortedDinos.get("inPark"));
            model.put("inNursery", sortedDinos.get("inNursery"));
            model.put("inContainment", sortedDinos.get("inContainment"));

            model.put("template", "templates/dinosaurs/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/dinosaurs/:id/feed", (req, res) -> {
            String stringId = req.params(":id");
            Integer intId = Integer.parseInt(stringId);
            Carnivore hungrydino = DBHelper.find(Carnivore.class, intId);
            hungrydino.getFed();
            DBHelper.saveOrUpdate(hungrydino);
            Map<String, Object> model = new HashMap<>();
            model.put("hungrydino", hungrydino);
            model.put("template", "templates/dinosaurs/feed.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/dinosaurs/:id/move", (req, res)->{
            String stringId = req.params(":id");
            Integer intId = Integer.parseInt(stringId);
            Dinosaur tobemoved = DBHelper.find(Dinosaur.class, intId);
            List<Paddock> paddocks = DBDinosaur.getAvailablePaddocks(tobemoved);
            Map<String, Object> model = new HashMap<>();
            model.put("dinosaur", tobemoved);
            model.put("paddocks", paddocks);
            model.put("template", "templates/dinosaurs/move.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/dinosaurs/:id", (req, res)->{
            String stringId = req.params(":id");
            Integer intId = Integer.parseInt(stringId);
            Dinosaur tobemoved = DBHelper.find(Dinosaur.class, intId);
            Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            tobemoved.setPaddock(paddock);
            DBHelper.saveOrUpdate(tobemoved);
            res.redirect("/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());


        get ("/dinosaurs/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            SpeciesType[] species = SpeciesType.values();
            model.put("species", species);
            model.put("template", "templates/dinosaurs/new.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/dinosaurs", (req, res) -> {
            String species = req.queryParams("species");
            String name = req.queryParams("name");
            int age = Integer.parseInt(req.queryParams("age"));
            int danger = Integer.parseInt(req.queryParams("danger"));

            Paddock nursery = DBHelper.find(Paddock.class, 1);

            if(species.equals(SpeciesType.TREX.getSpecies())){
                Carnivore dinosaur = new Carnivore(SpeciesType.TREX, name, age, danger, nursery);
                DBHelper.saveOrUpdate(dinosaur);
            } else if (species.equals(SpeciesType.VELOCIRAPTOR.getSpecies())) {
                Carnivore dinosaur = new Carnivore(SpeciesType.VELOCIRAPTOR, name, age, danger, nursery);
                DBHelper.saveOrUpdate(dinosaur);
            } else if(species.equals(SpeciesType.TRICERATOPS.getSpecies())){
                Herbivore dinosaur = new Herbivore(SpeciesType.TRICERATOPS, name, age, danger, nursery);
                DBHelper.saveOrUpdate(dinosaur);
            } else if(species.equals(SpeciesType.BRACHIOSAURUS.getSpecies())){
                Herbivore dinosaur = new Herbivore(SpeciesType.BRACHIOSAURUS, name, age, danger, nursery);
                DBHelper.saveOrUpdate(dinosaur);
            }
            res.redirect("/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());

        post ("/dinosaurs/:id/remove", (req, res) -> {
            int intId = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaurToDelete = DBHelper.find(Dinosaur.class, intId);
            DBHelper.delete(dinosaurToDelete);
            res.redirect("/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());

        post ("/dinosaurs/:id/capture", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(Dinosaur.class, id);
            DBDinosaur.capture(dinosaur);
            res.redirect("/dinosaurs");
            return null;
        }, new VelocityTemplateEngine());

        post("/dinosaurs/:id/containment", (req, res) -> {

            int id = Integer.parseInt(req.params(":id"));
            Dinosaur dinosaur = DBHelper.find(Dinosaur.class, id);

            Paddock containment = DBHelper.find(Paddock.class, 2);

            dinosaur.setPaddock(containment);
            DBHelper.saveOrUpdate(dinosaur);

            res.redirect("/dinosaurs");
            return null;

        }, new VelocityTemplateEngine());

        final ScheduledExecutorService hungerIncrease = Executors.newSingleThreadScheduledExecutor();
        hungerIncrease.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                List<Carnivore> carnivores = DBHelper.getAll(Carnivore.class);
                Collections.shuffle(carnivores);
                Carnivore carnivore = carnivores.get(0);
                carnivore.increaseHunger();
                DBHelper.saveOrUpdate(carnivore);
            }
        }, 2, 3, TimeUnit.SECONDS);

        final ScheduledExecutorService rampagingDinos = Executors.newSingleThreadScheduledExecutor();
        rampagingDinos.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                HashMap<String, List<Dinosaur>> sortedDinos = DBDinosaur.sortDinosaurs();
                List <Dinosaur> inPark = sortedDinos.get("inPark");
                Collections.shuffle(inPark);
                Dinosaur dinosaur = inPark.get(0);
                DBDinosaur.rampage(dinosaur);
            }
        }, 3, 10, TimeUnit.SECONDS);
    }
}
