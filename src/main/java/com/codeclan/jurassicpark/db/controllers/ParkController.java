package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.SparkBase.staticFileLocation;
import static spark.Spark.get;

public class ParkController {

    public static void main(String[] args) {

        Seeds.seedData();

        staticFileLocation("public");

        LoginController loginController = new LoginController();
        PaddockController paddockController = new PaddockController();
        DinosaurController dinosaurController = new DinosaurController();

        get("/", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            model.put("template", "home.vtl");

            return new ModelAndView(model, "templates/layout.vtl");


        }, new VelocityTemplateEngine());
    }


//        login page
//        home page



}
