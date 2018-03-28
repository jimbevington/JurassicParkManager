package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.*;
import com.codeclan.jurassicpark.db.models.*;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;


import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static spark.SparkBase.staticFileLocation;
import static spark.Spark.get;

public class ParkController {

    public static void main(String[] args) {

        Seeds.seedData();

        staticFileLocation("/public");

        VisitorController visitorController = new VisitorController();
        PaddockController paddockController = new PaddockController();
        DinosaurController dinosaurController = new DinosaurController();
        LoginController loginController = new LoginController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/home.vtl");
            List<Paddock> paddocks = DBPaddock.getParkPaddocks();
            model.put("paddocks", paddocks);
            List<Dinosaur> alerts = DBDinosaur.getDinoAlerts();
            model.put("alerts", alerts);
            DBDinosaur dbDinosaur = new DBDinosaur();
            model.put("dbDinosaur", dbDinosaur);
            int totalVisitors = DBHelper.totalVisitors();
            model.put("totalVisitors", totalVisitors);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/apocalypse", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            DBPaddock.apocalypseProtocol();

        }, new VelocityTemplateEngine());

    }
}
