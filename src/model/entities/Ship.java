package model.entities;

import model.Equation;
import model.Vector;

public class Ship extends Entity {

	double angle;

	public Ship(Vector position, Vector velocity, String name) {
		super(position,velocity,name);
		angle = 0;
		type = EntityType.SHIP;
	}


}
