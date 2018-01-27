package model;

import model.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class Chunk {
	public static final double length = 1000;
	public static final double width = 1000; //10,000km

	Map<Entity, Equation> entityEquationMap = new HashMap<>();
	private int x;
	private int y;

	public Chunk(int x, int y){
		this.x = x;
		this.y = y;
	}



}
