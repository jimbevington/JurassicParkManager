package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
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
            model.put("template", "templates/dinosaurs/index.vtl");

            List<Dinosaur> dinosaurs = DBHelper.getAll(Dinosaur.class);
            model.put("dinosaurs", dinosaurs);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());
    }
}
