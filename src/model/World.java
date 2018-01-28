package model;


import model.entities.Entity;

import java.time.Instant;
import java.util.*;

public class World extends Observable implements Observer{

	public static final double bigG = 6.67408e-11;
	public static final double lengthUnit = 1e7;
	public static final double timeUnit = 1;
	public static final long tickTime = 50;

	Chunk[][] chunks;
	Set<Entity> entities;

	double maxX;
	double maxY;

	private long baseTime;
	private long nextTime;
	int rows;
	int cols;

	public World(int rows, int cols){
		this.chunks = new Chunk[rows][cols];
		this.rows =rows;
		this.cols = cols;
		maxX = rows*Chunk.side;
		maxY = cols*Chunk.side;
		entities = new HashSet<>();
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				Chunk chunk = new Chunk(this,j*Chunk.side,i*Chunk.side);
				chunks[i][j] = chunk;
			}
		}
	}


	public void addEntity(Entity e){
		entities.add(e);
		placeEntity(e);
	}

	public void addEntities(Iterable<Entity> es){
		for(Entity e: es){
			addEntity(e);
		}
	}

	public Chunk getChunk(int row, int col){
		return chunks[row][col];
	}

	public void moved(Entity e){
		Vector pos = e.getPosition();
		Vector vel = e.getVelocity();
		if(pos.getX()<0){
			pos.setX(0);
			vel.setX(-vel.getX());
		}
		if(pos.getX()>maxX){
			pos.setX(maxX);
			vel.setX(-vel.getX());
		}
		if(pos.getY()<0){
			pos.setY(0);
			vel.setY(-vel.getX());
		}
		if(pos.getY()>maxY){
			pos.setY(maxY);
			vel.setY(-vel.getX());
		}


	}

	public void placeEntity(Entity e){
		Vector pos = e.getPosition();
		int i = (int)(pos.x/Chunk.side);
		int j = (int)(pos.y/Chunk.side);
		chunks[j][i].addEntity(e);
	}

	public void step(){
		for(int i = 0; i<rows; i++){
			for(int j =0;j<cols;j++){
				chunks[i][j].step(baseTime,nextTime);
			}
		}
		notifyObservers(entities);
	}

	public void run(){
		baseTime = Instant.now().toEpochMilli();
		while(true){
			nextTime = Instant.now().toEpochMilli();
			step();
			baseTime = nextTime;
		}
	}

	@Override
	public void update(Observable observable, Object o) {
		notifyObservers((Iterable<Entity>) o);
	}
}