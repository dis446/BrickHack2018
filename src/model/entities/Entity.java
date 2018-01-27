package model.entities;

import model.Vector;
import model.entities.EntityType;

public abstract class Entity {

	EntityType type = EntityType.NONE;
	Vector position;
	Vector velocity;
	Vector curAcceleration;
	String name;

	public Entity(Vector position, Vector velocity, String name){
		this.position = position;
		this.velocity = velocity;
		this.curAcceleration = new Vector(0,0);
		this.name = name;
	}

	public Entity(Vector position,String name){
		this(position,new Vector(0,0),name);
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

	public void applyAcc(Vector acc){
		curAcceleration = acc;
	}

	public void step(double timeStep){
		velocity.change(Vector.scalar(timeStep,curAcceleration));
		position.change(velocity);
	}

}
