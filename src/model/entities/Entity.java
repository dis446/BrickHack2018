package model.entities;

import model.Color;
import model.Vector;

public abstract class Entity {

	EntityType type = EntityType.NONE;
	Vector position;
	Vector velocity;
	Vector curAcceleration;
	String name;
	Color color;


	public Entity(Vector position, Vector velocity, String name, Color color){
		this.position = position;
		this.velocity = velocity;
		this.curAcceleration = new Vector(0,0);
		this.name = name;
		this.color = color;
	}

	public Entity(Vector position,String name, Color color){
		this(position,new Vector(0,0),name,color);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String out = "[\n"+type.getName()+": "+name+"\nPosition: "+position+"\n Velocity: "+velocity+"\n Acceleration: "
				+curAcceleration+"\n]";
		return out;
	}

	public EntityType getType() {
		return type;
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public Color getColor() { return color; }

	public boolean canMove(){return true;}

	public void applyAcc(Vector acc){
		curAcceleration = acc;
	}

	public void step(double timeStep){
		velocity.change(Vector.scalar(timeStep,curAcceleration));
		position.change(velocity);
	}

	public abstract String toFileFormat();

}
