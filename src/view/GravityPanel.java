package view;

import model.Vector;
import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;

import javax.swing.*;
import java.awt.*;

public class GravityPanel extends JPanel {
	private Iterable<Entity> entities;
	private GravityUI ui;

	double xScale;
	double yScale;

	public GravityPanel(GravityUI ui){
		this.ui = ui;
		this.xScale = ui.getXScale();
		this.yScale = ui.getYScale();
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Entity entity: entities){
			g2.setColor(ViewColor.modelToAWTColor(entity.getColor()));
			Vector startPos = entity.getPosition();
			int startX, startY;
			switch (entity.getType()){
				case SHIP:
					Ship ship = (Ship) entity;
					double size = ship.getSize();
					startX = (int) ((startPos.getX() - size) * xScale);
					startY = (int) ((startPos.getY() - size) * xScale);
					g2.fillRect(startX, startY, (int)(size * xScale), (int)(size * xScale));
					break;
				case PLANET:
					Planet planet= (Planet) entity;
					double radius = planet.getRadius();
					startX = (int) ((startPos.getX() - radius) * xScale);
					startY = (int) ((startPos.getY() - radius) * xScale);
					g2.fillOval(startX, startY, (int)(radius * 2 * xScale), (int)(radius * 2 * xScale));
					break;
				case NONE:
					break;
			}
		}
	}

	public void update(Iterable<Entity> entities){
		this.entities = entities;
		super.repaint();
	}

}
