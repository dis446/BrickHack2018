package model;


import model.entities.Entity;

import java.util.Observable;

public class World extends Observable{

	public static final double bigG = 6.67408e-11;
	public static final double lengthUnit = 1e7;
	public static final double timeUnit = 1;

	Chunk[][] chunks;

	public World(int rows, int cols){
		this.chunks = new Chunk[rows][cols];
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				Chunk chunk = new Chunk(this,j*Chunk.side,i*Chunk.side);
				chunks[i][j] = chunk;
			}
		}
	}

	public Chunk getChunk(int row, int col){
		return chunks[row][col];
	}

	public void moved(Entity e){}

	public void placeEntity(Entity e){
		Vector pos = e.getPosition();
		int i = (int)(pos.x/Chunk.side);
		int j = (int)(pos.y/Chunk.side);
		chunks[j][i].addEntity(e);
	}
}