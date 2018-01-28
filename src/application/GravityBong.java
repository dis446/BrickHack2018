package application;

import model.Chunk;
import model.Color;
import model.Vector;
import model.World;
import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;
import view.GravityUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GravityBong {

	World world;

	public GravityBong(int length, int column) {
		this.world = new World(length, column);
	}

	private void run() {
		GravityUI ui = new GravityUI(world);
	}

	private Iterable<Entity> readFromFile(String fileName){
		ArrayList<Entity> entities = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length < 8){
					throw new IllegalStateException("Error. Invalid line: " + line);
				}
				double positionX, positionY, velocityX, velocityY;
				String name;
				Color color;

				positionX = Double.parseDouble(data[1]);
				positionY = Double.parseDouble(data[2]);
				Vector position = new Vector(positionX, positionY);
				velocityX = Double.parseDouble(data[3]);
				velocityY = Double.parseDouble(data[4]);
				Vector velocity = new Vector(velocityX, velocityY);
				name = data[5];
				color = Color.parseColor(data[6]);

				if (data[0].equals("p")){
					//Planet
					double mass, radius;
					boolean canMove;
					mass = Double.parseDouble(data[7]);
					radius = Double.parseDouble(data[8]);
					canMove = Boolean.parseBoolean(data[9]); // We'll use this later
					entities.add(new Planet(position, velocity, name, mass, radius));
				}
				else{
					entities.add(new Ship(position, velocity, name));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entities;
	}

	public static void main(String[] args){
		if (args.length != 2){
			System.out.println("Error. Usage: java GravityBong length width");
			System.exit(0);
		}
		int length = Integer.parseInt(args[0]);
		int column = Integer.parseInt(args[1]);
		GravityBong gravityBong = new GravityBong(length, column);
		gravityBong.run();
	}
}
