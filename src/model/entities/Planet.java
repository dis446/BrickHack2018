package model.entities;

import model.Chunk;
import model.Equation;
import model.Vector;
import model.World;

public class Planet extends Entity {
	public static double minAcc = .05; //Minimum acceleration (m/s^2) considered significant

	double mass;
	double radius;
	boolean canMove;
	int SOI; // Sphere of Gravitational influence in terms of chunks
	Equation eq;

	public Planet(Vector position, Vector velocity, String name, double mass,double radius){
		super(position,velocity,name);
		type = EntityType.PLANET;
		this.mass = mass;
		this.radius = radius;
		canMove = true;
		SOI = (int) Math.ceil(Math.sqrt(World.bigG*mass/minAcc)/ Chunk.side) + 1;
		eq = (Vector pos) ->{
			Vector dir = Vector.sub(position,pos);
			double dis = dir.getLength();
			dir = dir.getUnit();
			return Vector.scalar(World.bigG*mass/(dis*dis),dir);
		};
	}

	public double getRadius(){
		return radius;
	}

	public boolean canMove(){return canMove;}

	@Override
	public String toFileFormat() {
		return "p,"+position.getX()+","+position.getY()+","+velocity.getX()+","+velocity.getY()+","+name+","+color+","
				+mass+","+radius+","+canMove;
	}

	public double getMass() {
		return mass;
	}

	public int getSOI() {
		return SOI;
	}

	public Equation getEq(){
		return eq;
	}

}
