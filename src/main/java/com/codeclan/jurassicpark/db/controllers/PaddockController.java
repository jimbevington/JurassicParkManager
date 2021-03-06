package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBActivity;
import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.DBPaddock;
import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import com.codeclan.jurassicpark.db.models.Visitor;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class PaddockController {

    public PaddockController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        //    update Paddock


        get("/paddocks", (req, res) -> {

            DBActivity.makeActivityDecision();  // decides whether to generate Activity

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/index.vtl");

            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            List<Paddock> parkPaddocks = DBPaddock.getParkPaddocks();
            model.put("parkPaddocks", parkPaddocks);

            Paddock nursery = DBHelper.find(Paddock.class, 1);
            List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(nursery);
            model.put("dinosaurs", dinosaurs);

            Paddock containment = DBHelper.find(Paddock.class, 2);
            List<Dinosaur> containDinos = DBHelper.getPaddocksDinosaurs(containment);
            model.put("containDinos", containDinos);

            DBPaddock dbPaddock = new DBPaddock();
            model.put("dbPaddock", dbPaddock);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        //    view Paddock with Details
        get("/paddocks/:id", (req, res) -> {

            DBActivity.makeActivityDecision();  // decides whether to generate Activity

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/view.vtl");

            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            model.put("paddock", paddock);

            List<Dinosaur> dinosaurs = DBHelper.getPaddocksDinosaurs(paddock);
            model.put("dinosaurs", dinosaurs);

            DBDinosaur dbDinosaur = new DBDinosaur();
            model.put("dbDinosaur", dbDinosaur);

            List<Visitor> visitors = DBHelper.getPaddocksVisitors(paddock);
            model.put("visitors", visitors);

            DBPaddock dbPaddock = new DBPaddock();
            model.put("dbPaddock", dbPaddock);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        get("/paddocks/:id/edit", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/edit.vtl");

            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            model.put("paddock", paddock);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


//    add Dinosaurs
        get("/paddocks/:id/add-dino", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/add-dino.vtl");

            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            model.put("paddock", paddock);

            List<Dinosaur> dinosaurs = DBPaddock.getAvailableDinosaurs(paddock);
            model.put("dinosaurs", dinosaurs);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());


        post("/paddocks/:id/edit", (req, res) -> {

            Integer paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock =  DBHelper.find(Paddock.class, paddockId);

            String name = req.queryParams("name");
            int capacity = Integer.parseInt(req.queryParams("capacity"));

            paddock.setName(name);
            paddock.setCapacity(capacity);

            DBHelper.saveOrUpdate(paddock);

            res.redirect("/paddocks/" + paddockId.toString());
            return null;

        }, new VelocityTemplateEngine());


        post("/paddocks/:id/add-dino", (req, res) -> {

            Integer paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);

            int dinoId = Integer.parseInt(req.queryParams("dinoId"));
            Dinosaur dinosaur = DBHelper.find(Dinosaur.class, dinoId);

            dinosaur.setPaddock(paddock);
            DBHelper.saveOrUpdate(dinosaur);

            res.redirect("/paddocks/" + paddockId.toString());
            return null;

        }, new VelocityTemplateEngine());


        post("/paddocks/:id/lock-down", (req, res) -> {

            Integer id = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, id);

            DBPaddock.lockDownPaddock(paddock);

            res.redirect("/paddocks/" + id.toString());
            return null;

        }, new VelocityTemplateEngine());


        post("/paddocks/:id/open", (req, res) -> {

            Integer id = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, id);

            DBPaddock.openPaddock(paddock);

            res.redirect("/paddocks/" + id.toString());
            return null;

        }, new VelocityTemplateEngine());

    }




}
