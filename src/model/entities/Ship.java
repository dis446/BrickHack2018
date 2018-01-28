package model.entities;

import model.Color;
import model.Vector;

public class Ship extends Entity {

	double angle;

	public Ship(Vector position, Vector velocity, String name, Color color) {
		super(position,velocity,name, color);
		angle = 0;
		type = EntityType.SHIP;
	}

	@Override
	public String toFileFormat() {
		return "s,"+position.getX()+","+position.getY()+","+velocity.getX()+","+velocity.getY()+","+name+","+color+","+
				angle;
	}


	public int getSize() {
		return 25;
	}
}
