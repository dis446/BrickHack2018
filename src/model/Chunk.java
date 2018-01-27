package model;

import model.entities.Entity;
import model.entities.Planet;

import java.time.Instant;
import java.util.*;

public class Chunk {
	public static final double side = 1000; //10,000km

	Set<Entity> entities;
	Set<Planet> planetInfluences;

	World world;

	double xPos;
	double yPos;

	public Chunk(World world, double xPos, double yPos){
		entities = new HashSet<>();
		planetInfluences = new HashSet<>();
		this.xPos = xPos;
		this.yPos = yPos;
		this.world = world;
	}

	public void addEntity(Entity e){
		entities.add(e);
		if(e instanceof Planet){
			planetInfluences.add((Planet) e);
		}
	}
	public void addInfluence(Planet e){
		planetInfluences.add(e);
	}

	public void removeEntity(Entity e){
		entities.remove(e);
	}

	public void removeInfluence(Planet p){
		planetInfluences.remove(p);
	}

	public boolean inBound(Entity e){
		Vector pos = e.getPosition();
		return (pos.getX()>= xPos && pos.getY()>= yPos && pos.getX()<xPos+side && pos.getY()<yPos+side);
	}

	public void step(int baseTime){
		for(Entity e: entities){
			Vector acc = new Vector();
			Vector pos = e.getPosition();
			for(Planet p: planetInfluences){
				acc.change(p.getEq().evaluate(pos));
			}
			e.applyAcc(acc);
			e.step((Instant.now().getNano()- baseTime)/1e9);
			if(!inBound(e)){
				world.moved(e);
			}
		}
	}



}
