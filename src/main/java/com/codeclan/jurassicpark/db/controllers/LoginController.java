package com.codeclan.jurassicpark.db.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    public LoginController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        post("/login", (req, res) -> {

            String user = req.queryParams("user");

            //  start the session Server-Side, add username attribute
            req.session().attribute("user", user);

            res.redirect("/");
            return null;

        }, new VelocityTemplateEngine());


        get("/login", (req, res) -> {

            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/login.vtl");

        }, new VelocityTemplateEngine());


        get("/logout", (req, res) -> {

            req.session().removeAttribute("username");
            res.redirect("/");

            return null;
        }, new VelocityTemplateEngine());

    }

    public static String getLoggedInUsername(Request req, Response res){

        String user = req.session().attribute("user");

        if (user == null || user.isEmpty()){
            res.redirect("/login");
        }

        return user;
    }


}
