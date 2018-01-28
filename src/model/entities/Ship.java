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

	@Override
	public String toFileFormat() {
		return "s,"+position.getX()+","+position.getY()+","+velocity.getX()+","+velocity.getY()+","+name+","+color+","+
				angle;
	}


	public int getSize() {
		return 10;
	}
}
