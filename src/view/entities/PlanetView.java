package view.entities;

import model.Equation;
import model.Vector;
import model.entities.EntityType;
import model.entities.Planet;

import java.awt.*;
import java.util.Random;

public class PlanetView implements EntityView {

	Planet basePlanet;
	Color color;

	public PlanetView(Planet basePlanet){
		this.basePlanet = basePlanet;
		Random random = new Random();
		this.color = new Color(random.nextInt(), random.nextInt(), random.nextInt());
	}

	public PlanetView(Planet basePlanet, Color color){
		this.basePlanet = basePlanet;
		this.color = color;
	}

	public double getRadius(){
		return basePlanet.getRadius();
	}

	@Override
	public EntityType getType() {
		return EntityType.PLANET;
	}

	@Override
	public Vector getPosition() {
		return basePlanet.getPosition();
	}

	@Override
	public Vector getVelocity() {
		return basePlanet.getVelocity();
	}

	@Override
	public Color getColor() {
		return this.color;
	}
}
