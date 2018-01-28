package view;

import model.Chunk;
import model.World;
import model.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GravityUI extends JFrame implements Observer{

	World world;
	GravityPanel panel;
	public double xScaleFactor = 1.0;
	public double yScaleFactor = 1.0;

	public GravityUI(World world){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		this.world = world;
		this.world.addObserver(this);
		xScaleFactor = (float) (width) / (this.world.getNumRows() * Chunk.side);
		yScaleFactor = (float) (height) / (this.world.getNumCols() * Chunk.side);
		this.setTitle("Gravity Bong Simulation");
		this.panel = new GravityPanel(this);
		this.add(panel);
		setSize(width, height);
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
		if (!(o instanceof World)){
			throw new IllegalArgumentException("Error. Argument of wrong type.");
		}
		this.world = (World) o;
		if (!(arg instanceof Iterable)){
			throw new IllegalArgumentException("Error. Argument of wrong type.");
		}
		drawAll(((Iterable<Entity>) arg));
	}

	public double getXScale() {
		return xScaleFactor;
	}
	public double getYScale() {
		return yScaleFactor;
	}
}
