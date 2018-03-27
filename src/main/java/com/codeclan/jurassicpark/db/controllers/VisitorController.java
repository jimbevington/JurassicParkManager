package com.codeclan.jurassicpark.db.controllers;

import com.codeclan.jurassicpark.db.db.DBHelper;
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

            List<Visitor> visitors = DBHelper.getAll(Visitor.class);
            model.put("visitors", visitors);

            List<Visitor> visitorsInPark = DBVisitor.inPark();
            model.put("visitorsInPark", visitorsInPark);
            List<Visitor> visitorsNotInPark = DBVisitor.notInPark();
            model.put("visitorsNotInPark", visitorsNotInPark);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }
}
