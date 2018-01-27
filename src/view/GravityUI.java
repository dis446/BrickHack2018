package view;

import model.World;

import java.awt.*;

public class GravityUI extends Frame {

	World world;
	GravityPanel panel;

	public GravityUI(World world){
		this.world = world;
		this.setTitle("Gravity Bong Simulation");
		this.panel = new GravityPanel();
	}


	public static void main(String args[]){
		new GravityUI(new World(5, 5));
	}


}
