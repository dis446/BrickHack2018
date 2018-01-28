package view;

import model.World;
import model.entities.Entity;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class GravityUI extends JFrame implements Observer{

	public static final int xDimension = 1000;
	public static final int yDimension = 1000;

	World world;
	GravityPanel panel;

	public GravityUI(World world){
		this.world = world;
		this.setTitle("Gravity Bong Simulation");
		this.panel = new GravityPanel();
		this.add(panel);
		setSize(xDimension, yDimension);
		setVisible(true);
	}

	private void drawAll(Iterable<Entity> entities){
		panel.update(entities);
	}

	public static void main(String args[]){
		new GravityUI(new World(5, 5));
	}

	@Override
	public void update(Observable o, Object arg) {
		this.world = (World) o;
		drawAll((Iterable<Entity>) arg);
	}
}
