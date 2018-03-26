package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.models.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

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
    }


}
