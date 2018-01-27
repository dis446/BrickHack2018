package view;

import model.World;

import java.awt.*;

public class GravityUI extends Frame {

	public static final int xDimension = 500;
	public static final int yDimension = 500;


	World world;
	GravityPanel panel;

	public GravityUI(World world){
		this.world = world;
		this.setTitle("Gravity Bong Simulation");
		this.panel = new GravityPanel();
		setSize(xDimension, yDimension);
		setVisible(true);
	}


	public static void main(String args[]){
		new GravityUI(new World(5, 5));
	}


}
