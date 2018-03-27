package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.Seeds;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import com.codeclan.jurassicpark.db.models.Visitor;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//            String loggedInUser = LoginController.getLoggedInUsername(req, res);
//            model.put("user", loggedInUser);

            model.put("template", "templates/home.vtl");


            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);

            int totalVisitors = DBHelper.totalVisitors();
            model.put("totalVisitors", totalVisitors);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
    }




//        login page
//        home page



}
