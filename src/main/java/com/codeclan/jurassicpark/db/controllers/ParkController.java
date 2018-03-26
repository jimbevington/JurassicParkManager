package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.Seeds;
import com.codeclan.jurassicpark.db.models.Paddock;
import spark.ModelAndView;
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

        PaddockController paddockController = new PaddockController();
        DinosaurController dinosaurController = new DinosaurController();

        get("/", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/home.vtl");

//            String loggedInUser = LoginController.getLoggedInUsername(req, res);
//            model.put("user", loggedInUser);


            return new ModelAndView(model, "templates/layout.vtl");


        }, new VelocityTemplateEngine());
    }


//        login page
//        home page



}
