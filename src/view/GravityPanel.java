package view;

import model.Vector;
import model.entities.Entity;
import model.entities.Planet;
import model.entities.Ship;

import javax.swing.*;
import java.awt.*;

public class GravityPanel extends JPanel {
	private Iterable<Entity> entities;

	public GravityPanel(){
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
					int size = ship.getSize();
					startX = (int) (startPos.getX() - size);
					startY = (int) (startPos.getY() - size);
					g2.fillRect(startX, startY, size, size);
					break;
				case PLANET:
					Planet planet= (Planet) entity;
					int radius = (int) planet.getRadius();
					startX = (int) (startPos.getX() - radius);
					startY = (int) (startPos.getY() - radius);
					g2.fillOval(startX, startY, radius * 2, radius * 2);
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
