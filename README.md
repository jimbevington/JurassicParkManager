# Jurassic Park Manager

Paired project with Christie Lester according to a brief provided by Codeclan.

Built in Java, the system incorporates the Hibernate ORM framework for database interaction.
The web app is implemented using Spark, HTML and CSS.
Strictly NO Javascript or additional web frameworks permitted.

## Overview

Simulates a Park Manager web app for Jurassic Park. Users can:

* monitor key Park information from the Dashboard
* view further information on Paddocks, Dinosaurs and Visitors
* move Dinosaurs between Paddocks (where appropriate)
* add newly born Dinosaurs to the Nursery paddock
* feed hungry Carnivores
* deal with escaped Dinosaurs (lock down Paddock and capture escapee)

To prevent carnage/loss of valuable assets, the app only allows carnivorous Dinos to be moved into paddocks with others of the same species.

Given that safety is Jurassic Parks No. 1 priority, the front-end dynamically updates to alert the user that action should be taken.

## Using the App

Clone the project onto your own machine.

In Terminal, create a database to store the Park information (AND TO TEST?)
```
createdb jurassicparkdb
```
