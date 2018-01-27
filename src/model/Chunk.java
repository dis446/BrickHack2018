package model;

import java.util.HashMap;
import java.util.Map;

public class Chunk {

	Map<Entity, Equation> entityEquationMap = new HashMap<>();
	private int x;
	private int y;

	public Chunk(int x, int y){


		this.x = x;
		this.y = y;
	}

}
