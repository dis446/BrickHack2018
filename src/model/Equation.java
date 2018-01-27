package model;

public class Equation {

	private int x;
	private int y;
	private Entity entity;

	public Equation(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setEntity(Entity entity){
		this.entity = entity;
	}

}
