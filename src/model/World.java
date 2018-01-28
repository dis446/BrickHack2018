package model;


import model.entities.Entity;
import model.entities.Planet;

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

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
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

		if(e instanceof Planet){
			Planet p = (Planet) e;
			removeInfluence(p,p.getSOI()+2 );
		}
		placeEntity(e);
	}

	public void removeInfluence(Planet p, int radius){
		Vector pos = p.getPosition();
		int row = (int) (pos.getY()/Chunk.side);
		int col = (int) (pos.getX()/Chunk.side);
		Queue<Vector> todo = new ArrayDeque<>();
		Set<Vector> done = new HashSet<>();
		todo.add(new Vector(row,col));
		while(!todo.isEmpty()){
			Vector v = todo.poll();
			if(done.contains(v)){continue;}
			int diff = (int) (Math.abs(v.x-row)+Math.abs(v.y-col));
			chunks[(int)v.x][(int)v.y].removeInfluence(p);
			if(diff<=radius) {
				todo.add(Vector.add(v, new Vector(0, 1)));
				todo.add(Vector.add(v, new Vector(0, -1)));
				todo.add(Vector.add(v, new Vector(1, 0)));
				todo.add(Vector.add(v, new Vector(-1, 0)));
			}
		}

	}

	public void addInfluence(Planet p, int radius){
		Vector pos = p.getPosition();
		int row = (int) (pos.getY()/Chunk.side);
		int col = (int) (pos.getX()/Chunk.side);
		Queue<Vector> todo = new ArrayDeque<>();
		Set<Vector> done = new HashSet<>();
		todo.add(new Vector(row,col));
		while(!todo.isEmpty()){
			Vector v = todo.poll();
			if(done.contains(v) || v.x<0 || v.x>=rows || v.y<0 || v.y>=cols){
				continue;
			}
			int diff = (int) (Math.abs(v.x-row)+Math.abs(v.y-col));
			chunks[(int)v.x][(int)v.y].addInfluence(p);
			if(diff<=radius) {
				todo.add(Vector.add(v, new Vector(0, 1)));
				todo.add(Vector.add(v, new Vector(0, -1)));
				todo.add(Vector.add(v, new Vector(1, 0)));
				todo.add(Vector.add(v, new Vector(-1, 0)));
			}
		}

	}

	public void placeEntity(Entity e){
		Vector pos = e.getPosition();
		int i = (int)(pos.x/Chunk.side);
		int j = (int)(pos.y/Chunk.side);
		chunks[j][i].addEntity(e);
		if(e instanceof Planet){
			Planet p = (Planet) e;
			addInfluence(p,p.getSOI());
		}
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