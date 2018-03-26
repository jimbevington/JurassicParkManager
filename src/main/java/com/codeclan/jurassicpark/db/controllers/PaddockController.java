package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.DBPaddock;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class PaddockController {

    public PaddockController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/paddocks", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/index.vtl");

            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        //    view Paddock with Details
        get("/paddocks/:id", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/paddocks/view/vtl");

            int paddockId = Integer.parseInt(req.params(":id"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            model.put("paddock", paddock);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//    update Paddock
//    add Dinosaurs

    }




}
