package application;

import model.Chunk;
import model.World;
import view.GravityUI;

public class GravityBong {

	World world;

	public GravityBong(int length, int column) {
		this.world = new World(length, column);
	}

	private void run() {
		GravityUI ui = new GravityUI(world);
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
