package model.entities;

import model.Equation;
import model.Vector;

public class Planet extends Entity {

	public Planet(Vector position, Vector velocity, String name, double mass){
		super(new Vector(0,0),new Vector(0,0),"hi");
	}
}
