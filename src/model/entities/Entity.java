package model.entities;

import model.Texture;
import model.Vector;

public abstract class Entity {

	EntityType type = EntityType.NONE;
	Vector position;
	Vector velocity;
	Vector curAcceleration;
	String name;
	Texture color;


	public Entity(Vector position, Vector velocity, String name, Texture color){
		this.position = position;
		this.velocity = velocity;
		this.curAcceleration = new Vector(0,0);
		this.name = name;
		this.color = color;
	}

	public Entity(Vector position,String name, Texture color){
		this(position,new Vector(0,0),name,color);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String out = "["+type.getName()+": "+name+" Position: "+position+" Velocity: "+velocity+" Acceleration: "
				+curAcceleration+"]";
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

	public Texture getColor() { return color; }

	public boolean canMove(){return true;}

	public void applyAcc(Vector acc){
		curAcceleration = acc;
	}

	public void step(double timeStep){
		velocity.change(Vector.scalar(timeStep,curAcceleration));
		position.change(Vector.scalar(timeStep, velocity));
	}

	public abstract String toFileFormat();

}
