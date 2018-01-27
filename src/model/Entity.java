package model;

public abstract class Entity {
	private Equation equation;

	public Entity(Equation equation){
		this.equation = equation;
		equation.setEntity(this);
	}



}
