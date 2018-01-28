package _tests;

import model.Vector;
import model.World;
import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;
import view.GravityUI;

import java.util.ArrayList;

public class UITester {
	public static void main(String[] args) throws InterruptedException {
		GravityUI ui = new GravityUI(new World(5,5));
		ArrayList<Entity> testPlanets = new ArrayList<>();
		testPlanets.add(new Planet(new Vector(50, 50), Vector.nullVector(), "testPlanet1", 10.0, 30.0 ));
		testPlanets.add(new Ship(new Vector(100, 100), Vector.nullVector(), "testShip1"));
		ui.drawAll(testPlanets);

		Thread.sleep(2000);
		testPlanets.add(new Planet(new Vector(200, 200), Vector.nullVector(), "testPlanet2", 10.0, 50.0));
		ui.drawAll(testPlanets);

		Thread.sleep(1500);
		testPlanets = new ArrayList<>();
		ui.drawAll(testPlanets);

	}
}
