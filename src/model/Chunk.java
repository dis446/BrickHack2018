package model;

import model.entities.Entity;
import model.entities.Planet;

import java.time.Instant;
import java.util.*;

public class Chunk extends Observable{
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

	/**
	 * Add a given entity to this chunk.
	 * @param e
	 */
	public void addEntity(Entity e){
		entities.add(e);
		if(e instanceof Planet){
			planetInfluences.add((Planet) e);
		}
	}

	/**
	 * Add a given planet to the list of influencers on this chunk.
	 * @param e
	 */
	public void addInfluence(Planet e){
		planetInfluences.add(e);
	}

	/**
	 * Remove a given entity from this chunk
	 * @param e
	 */
	public void removeEntity(Entity e){
		entities.remove(e);
	}

	/**
	 * Remove a given planet from the list of influencers on this chunk.
	 * @param p
	 */
	public void removeInfluence(Planet p){
		planetInfluences.remove(p);
	}

	/**
	 * Boolean check to see if a given entity is within the bounds of this chunk.
	 * @param e
	 * @return
	 */
	public boolean inBound(Entity e){
		Vector pos = e.getPosition();
		return (pos.getX()>= xPos && pos.getY()>= yPos && pos.getX()<xPos+side && pos.getY()<yPos+side);
	}

	/**
	 * Apply the changes to this chunk(and all entities contained within) based on the difference between two given times.
	 * @param baseTime
	 * @param newTime
	 */
	public void step(long baseTime, long newTime){
		Set<Entity> copy = new HashSet<>(entities);
		for(Entity e: copy) {
			Vector acc = new Vector();
			Vector pos = e.getPosition();
			for (Planet p : planetInfluences) {
				if (!p.equals(e)) {
					acc.change(p.getEq().evaluate(pos));

				}
			}
			e.applyAcc(acc);
			e.step((Math.min((newTime - baseTime), World.tickTime) / 1e3));
			if (!inBound(e)) {
				entities.remove(e);
				world.moved(e);
			}
		}
	}

	/**
	 * Return a string of the format: "[Vector
	 * 	Entity 1
	 * 	Entity 2
	 * 	Entity 3
	 * 	...
	 * 	]
	 * @return
	 */
	@Override
	public String toString() {
		String out = "[ "+ new Vector(xPos,yPos)+"\n";
		for(Entity e:entities){
			out+= e+"\n";
		}
		out+="]";
		return out;
	}
}
