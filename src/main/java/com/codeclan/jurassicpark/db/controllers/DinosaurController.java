package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBDinosaur;
import com.codeclan.jurassicpark.db.models.Dinosaur;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

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
    }
}
