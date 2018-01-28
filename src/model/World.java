package model;


import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;

import java.time.Instant;
import java.util.*;

public class World extends Observable{

	public static final double bigG = 6.67408e-11;
	public static final long lengthUnit = (long) 1e7;
	public static final long timeUnit = (long) 1;
	public static final long massUnit = (long) 1e9;
	public static final long tickTime = 50;

	Chunk[][] chunks;
	Set<Entity> entities;

	double maxX;
	double maxY;

	private long baseTime;
	private long nextTime;
	int rows;
	int cols;

	public World(int rows, int cols) {
		this.chunks = new Chunk[rows][cols];
		this.rows = rows;
		this.cols = cols;
		maxX = rows * Chunk.side;
		maxY = cols * Chunk.side;
		entities = new HashSet<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Chunk chunk = new Chunk(this, j * Chunk.side, i * Chunk.side);
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

	public Set<Entity> getEntities() {
		return entities;
	}

	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return cols;
	}

	public void moved(Entity e){
		Vector pos = e.getPosition();
		Vector vel = e.getVelocity();
		if(pos.getX()<0){
			pos.setX(0);
			vel.setX(-vel.getX());
		}
		if(pos.getX()>=maxX){
			pos.setX(maxX-1);
			vel.setX(-vel.getX());
		}
		if(pos.getY()<0){
			pos.setY(0);
			vel.setY(-vel.getX());
		}
		if(pos.getY()>=maxY){
			pos.setY(maxY-1);
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
			if(done.contains(v) || !inBounds(v)){
				continue;
			}
			int diff = (int) (Math.abs(v.x-row)+Math.abs(v.y-col));
			chunks[(int)v.x][(int)v.y].removeInfluence(p);
			if(diff<=radius) {
				Vector up = Vector.add(v, new Vector(0, 1));
				if(!done.contains(up) && inBounds(up)) {
					todo.add(up);
				}
				Vector down = Vector.add(v, new Vector(0, -1));
				if(!done.contains(down) && inBounds(down)) {
					todo.add(down);
				}
				Vector left = Vector.add(v, new Vector(1, 0));
				if(!done.contains(left) && inBounds(left)) {
					todo.add(up);
				}
				Vector right = Vector.add(v, new Vector(-1, 0));
				if(!done.contains(right) && inBounds(right)) {
					todo.add(right);
				}
				done.add(v);
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
			if(done.contains(v) || !inBounds(v)){
				continue;
			}
			int diff = (int) (Math.abs(v.x-row)+Math.abs(v.y-col));
			chunks[(int)v.x][(int)v.y].addInfluence(p);
			if(diff<=radius) {
				Vector up = Vector.add(v, new Vector(0, 1));
				if(!done.contains(up) && inBounds(up)) {
					todo.add(up);
				}
				Vector down = Vector.add(v, new Vector(0, -1));
				if(!done.contains(down) && inBounds(down)) {
					todo.add(down);
				}
				Vector left = Vector.add(v, new Vector(1, 0));
				if(!done.contains(left) && inBounds(left)) {
					todo.add(left);
				}
				Vector right = Vector.add(v, new Vector(-1, 0));
				if(!done.contains(right) && inBounds(right)) {
					todo.add(right);
				}
				done.add(v);
			}
		}

	}

	/**
	 * Checks if this <row,col> vector is in bounds
	 * @param v
	 * @return
	 */
	public boolean inBounds(Vector v){
		return v.x>=0 && v.y>=0 && v.x<cols && v.y<rows;
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
		setChanged();
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
	public String toString() {
		String out = "World:\n";
		for(int i = 0; i<rows;i++){
			for(int j = 0; j<cols;j++){
				out+="==========================\n";
				out+=chunks[i][j]+"\n";
				out+="==========================\n";

			}
		}
		return out;
	}

	public static void main(String[] args){
	    ///Current config is when my computer reaches ~20 ticks/s
		World world = new World(5,5);
		world.baseTime = 0;
		world.nextTime = 50;
		//world.addEntity(new Ship(new Vector(990,990),new Vector(1000,0),"bbygurl",Color.RED));
        int count = 300;
        Random random = new Random();
        while(count>0){
            world.addEntity(new Planet(new Vector(random.nextDouble()%world.maxX,random.nextDouble()%world.maxY),new Vector(),"kepler",random.nextLong()%300000+100000,3,Texture.RED));
            world.addEntity(new Ship(new Vector(random.nextDouble()%world.maxX,random.nextDouble()%world.maxY),new Vector(),"kepler",Texture.RED));
            count--;
        }

		int i = 0;
		//System.out.println(world);
		world.baseTime = Instant.now().toEpochMilli();
		long start = Instant.now().getEpochSecond();
		while(Instant.now().getEpochSecond() - start <10){
			world.nextTime = Instant.now().toEpochMilli();
			world.step();
			world.baseTime = world.nextTime;

			//System.out.println(world);
			i++;
		}
		System.out.println(world);
		System.out.println(i);
	}
}