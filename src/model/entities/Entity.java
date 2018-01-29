package model.entities;

import model.Texture;
import model.Vector;

public abstract class Entity {

	/**
	 * Stores the subtype of entity that this instance is of.
	 */
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

	/**
	 * Creates an entity with zero initial velocity
	 * @param position
	 * @param name
	 * @param color
	 */
	public Entity(Vector position,String name, Texture color){
		this(position,new Vector(0,0),name,color);
	}

	/**
	 * Getter for the name.
	 * @return string
	 */
	public String getName() {
		return name;
	}

	/**
	 * ToString in the form: "[type: Position: pos Velocity: vel Acceleration: acc]"
	 * @return
	 */
	@Override
	public String toString() {
		String out = "["+type.getName()+": "+name+" Position: "+position+" Velocity: "+velocity+" Acceleration: "
				+curAcceleration+"]";
		return out;
	}

	/**
	 * Getter for the type.
	 * @return
	 */
	public EntityType getType() {
		return type;
	}

	/**
	 * Get the position vector.
	 * @return
	 */
	public Vector getPosition() {
		return position;
	}

	/**
	 * Getter for the velocity vector.
	 * @return
	 */
	public Vector getVelocity() {
		return velocity;
	}

	/**
	 * Getter for the color.
	 * @return
	 */
	public Texture getColor() { return color; }

	/**
	 * Getter for canMove
	 * @return
	 */
	public boolean canMove(){return true;}

	/**
	 * Setter for the acceleration vector.
	 * @param acc
	 */
	public void applyAcc(Vector acc){
		curAcceleration = acc;
	}

	/**
	 * Apply the changes that a difference in time causes on this entities velocity and position.
	 * @param timeStep
	 */
	public void step(double timeStep){
		velocity.change(Vector.scalar(timeStep,curAcceleration));
		position.change(Vector.scalar(timeStep, velocity));
	}

	/**
	 * Output a String in the format that can be stored into a file.
	 * @return
	 */
	public abstract String toFileFormat();

}
