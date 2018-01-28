package application;

import model.Texture;
import model.Vector;
import model.World;
import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;
import view.GravityUI;

import java.io.*;
import java.util.ArrayList;

public class GravityBomb {

	World world;
	GravityUI gui;

	public GravityBomb(int length, int column) {
		this.world = new World(length, column);
		this.gui = new GravityUI(world);
	}

	public GravityBomb(String fileName) {
		readFromFile(fileName);
		this.gui = new GravityUI(this.world);
	}

	private void run() {
		this.world.run();
	}

	private void readFromFile(String fileName){
		ArrayList<Entity> entities = new ArrayList<>();
		int rows, cols;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			line = bufferedReader.readLine();
			String[] rowCol = line.split(",");
			rows = Integer.parseInt(rowCol[0]);
			cols = Integer.parseInt(rowCol[1]);
			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length < 8){
					throw new IllegalStateException("Error. Invalid line: " + line);
				}
				double positionX, positionY, velocityX, velocityY;
				String name;
				Texture color;

				positionX = Double.parseDouble(data[1]);
				positionY = Double.parseDouble(data[2]);
				Vector position = new Vector(positionX, positionY);
				velocityX = Double.parseDouble(data[3]);
				velocityY = Double.parseDouble(data[4]);
				Vector velocity = new Vector(velocityX, velocityY);
				name = data[5];
				color = Texture.parseColor(data[6]);

				if (data[0].equals("p")){
					//Planet
					double mass, radius;
					boolean canMove;
					mass = Double.parseDouble(data[7]);
					radius = Double.parseDouble(data[8]);
					canMove = Boolean.parseBoolean(data[9]); // We'll use this later
					entities.add(new Planet(position, velocity, name, mass, radius, color));
				}
				else{
					entities.add(new Ship(position, velocity, name, color));
				}
			}
			this.world = new World(rows, cols);
			this.world.addEntities(entities);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveWorld(World world, String fileName){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
			writer.write(world.getNumRows() + "," + world.getNumCols());
			writer.newLine();
			for (Entity entity: world.getEntities()){
				writer.write(entity.toFileFormat());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args){
		GravityBomb gravityBomb = null;
		if (args.length == 1){
			String fileName = args[0];
			gravityBomb = new GravityBomb(fileName);
		} else if (args.length == 2){
			int length = Integer.parseInt(args[0]);
			int width = Integer.parseInt(args[1]);
			gravityBomb = new GravityBomb(length, width);
		}else{
			System.out.println("Error. Usage: java GravityBomb length width or java GravityBomb fileName");
			System.exit(0);
		}
		gravityBomb.run();
	}
}
