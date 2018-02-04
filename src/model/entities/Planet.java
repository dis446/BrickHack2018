package model.entities;

import model.*;

/**
 * An object whose mass is considered significant enough to affect other entities
 */
public class Planet extends Entity {
	public static double minAcc = .01; //Minimum acceleration (m/s^2) considered significant

	/**
	 * the mass of this planet in World.massUnit*kg
	 */
	double mass;
	/**
	 * the radius of this planet in m
	 */
	double radius;

	/**
	 * whether or not this planet's position can change (Not Implemented)
	 */
	boolean canMove;
	int SOI; // Sphere of Gravitational influence in terms of chunks
	/**
	 * equation that takes in a position vector that calculates the acceleration cause by this planet
	 */
	Equation eq;

	/**
	 * Creates a planet
	 * @param position Starting position of the planet
	 * @param velocity Initial velocity
	 * @param name name of planet
	 * @param mass the mass of the planet in World.massUnit*kg
	 * @param radius the radius of the planet in meters
	 * @param color the color of this planet
	 */
	public Planet(Vector position, Vector velocity, String name, double mass, double radius, Texture color){
		super(position,velocity,name, color);
		type = EntityType.PLANET;
		this.mass = mass;
		this.radius = radius;
		canMove = true;
		SOI = (int) Math.ceil(Math.sqrt(World.bigG*mass*World.massUnit/minAcc)/ Chunk.side) + 1;
		eq = (Vector pos) ->{
			Vector dir = Vector.sub(position,pos);
			double dis = dir.getLength();
			if(dis == 0){
				return new Vector();
			}
			dir = dir.getUnit();
			return Vector.scalar(World.bigG*mass*World.massUnit/(dis*dis),dir);
		};
	}

	/**
	 * @return radius of this planet
	 */
	public double getRadius(){
		return radius;
	}

	/**
	 * UNUSED and UNIMPLEMENTED
	 * @return whether or not this planet can move
	 */
	public boolean canMove(){return canMove;}

	/**
	 * @return file representation of this planet
	 */
	@Override
	public String toFileFormat() {
		return "p,"+position.getX()+","+position.getY()+","+velocity.getX()+","+velocity.getY()+","+name+","+color+","
				+mass+","+radius+","+canMove;
	}

	/**
	 * @return mass of this planet in World.massUnit * kg
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Works basically the same but allows for a planet to be uninfluenced
	 * @param acc
	 */
	@Override
	public void applyAcc(Vector acc) {
		if(canMove) {
			super.applyAcc(acc);
		}
	}

	/**
	 * @return How many chunks around this planet are considered close enough for the gravitational effect to be significant
	 */
	public int getSOI() {
		return SOI;
	}

	/**
	 * @return this planet's gravitational acceleration equation
	 */
	public Equation getEq(){
		return eq;
	}

}
