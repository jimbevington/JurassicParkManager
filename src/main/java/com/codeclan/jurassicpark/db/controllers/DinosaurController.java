package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class DinosaurController {

    public DinosaurController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/dinosaurs", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Dinosaur> dinosaurs = DBDinosaur.listAll();
            model.put("dinosaurs", dinosaurs);
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

//        get("/dinosaurs/:id/move", (req, res)->{
//            String stringId = req.params(":id");
//            Integer intId = Integer.parseInt(stringId);
//            Dinosaur tobemoved = DBHelper.find(Dinosaur.class, intId);
//            List<Paddock> paddocks = DBDinosaur.getAvailablePaddocks(tobemoved);
//            Map<String, Object> model = new HashMap<>();
//            model.put("tobemoved", tobemoved);
//            model.put("template", "templates/dinosaurs/move.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//        });

        get("/dinosaurs/:id/move", (req, res) -> {
            String stringId = req.params(":id");
            Integer intId = Integer.parseInt(stringId);
            Dinosaur tobemoved = DBHelper.find(Dinosaur.class, intId);
            List<Paddock> availablepaddocks = DBDinosaur.getAvailablePaddocks(tobemoved);
            Map<String, Object> model = new HashMap<>();
            model.put("availablepaddocks", availablepaddocks);
            model.put("template", "templates/dinosaurs/move.vtl");
            model.put("dinosaur", tobemoved);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/dinosaurs", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Dinosaur dinosaur = DBHelper.find(Dinosaur.class, intId);
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
//            SpeciesType species = req.queryParams("species");
            String name = req.queryParams("name");
            int age = Integer.parseInt(req.queryParams("age"));
            int danger = Integer.parseInt(req.queryParams("danger"));

//            dinosaur.setSpecies(species);
            dinosaur.setName(name);
            dinosaur.setPaddock(paddock);
            dinosaur.setAge(age);
            dinosaur.setDanger(danger);
            DBHelper.saveOrUpdate(dinosaur);
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
    }
}
