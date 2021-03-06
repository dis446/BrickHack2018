package _tests;

import model.Texture;
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
		testPlanets.add(new Planet(new Vector(50, 50), new Vector(), "testPlanet1", 10.0, 30.0 , Texture.RED));
		testPlanets.add(new Ship(new Vector(100, 100), new Vector(), "testShip1", Texture.BLUE));
		ui.update(null, testPlanets);

		Thread.sleep(2000);
		testPlanets.add(new Planet(new Vector(200, 200), new Vector(), "testPlanet2", 10.0, 50.0, Texture.WHITE));
		ui.update(null, testPlanets);

		Thread.sleep(1500);
		testPlanets = new ArrayList<>();
		ui.update(null, testPlanets);

	}
}
