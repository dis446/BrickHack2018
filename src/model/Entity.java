package model;

public abstract class Entity {
	Vector position;
	Vector velocity;
	Vector curAcceleration;

	public Entity(Vector position, Vector velocity){
		this.position = position;
		this.velocity = velocity;
		this.curAcceleration = new Vector(0,0);
	}

	public Entity(Vector position){
		this(position,new Vector(0,0));
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
