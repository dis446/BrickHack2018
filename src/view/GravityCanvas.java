package view;

import model.Vector;
import model.entities.Planet;
import view.entities.EntityView;
import view.entities.PlanetView;
import view.entities.ShipView;

import java.awt.*;

public class GravityCanvas extends Canvas {

	public GravityCanvas(){

	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
	}

	public void paint(Graphics g, EntityView entity){
		paint(g);
		Vector startPos = entity.getPosition();
		Graphics2D g2 = (Graphics2D) g;
		int startX;
		int startY;

		switch (entity.getType()){
			case PLANET:
				PlanetView planetView = (PlanetView) entity;
				final int radius = (int) planetView.getRadius();
				startX = (int)startPos.getX() - radius;
				startY = (int)startPos.getY() - radius;
				g2.fillOval(startX, startY, radius * 2, radius * 2);
				break;
			case SHIP:
				ShipView shipView = (ShipView) entity;
				final int size = shipView.getSize();
				startX = (int) startPos.getX() - size;
				startY = (int) startPos.getX() - size;
				g2.draw(new Rectangle(startX, startY, size, size));
				break;
			case NONE:
				throw new IllegalStateException("Trying to draw none!");
		}
		g2.setColor(entity.getColor());
	}

}
