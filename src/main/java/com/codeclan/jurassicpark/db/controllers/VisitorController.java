package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
import com.codeclan.jurassicpark.db.db.DBVisitor;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class VisitorController {

    public VisitorController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/visitors", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/visitors/index.vtl");

            HashMap<String, List<Visitor>> visitors = DBVisitor.sortVisitors();
            model.put("visitors", visitors.get("all"));
            model.put("inPark", visitors.get("inPark"));
            model.put("notInPark", visitors.get("notInPark"));

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }
}
