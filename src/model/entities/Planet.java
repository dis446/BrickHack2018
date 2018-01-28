package model.entities;

import model.*;

public class Planet extends Entity {
	public static double minAcc = .01; //Minimum acceleration (m/s^2) considered significant

	double mass;
	double radius;
	boolean canMove;
	int SOI; // Sphere of Gravitational influence in terms of chunks
	Equation eq;

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
