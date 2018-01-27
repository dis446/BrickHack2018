package view;

import model.Vector;
import view.entities.EntityView;
import view.entities.PlanetView;
import view.entities.ShipView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GravityPanel extends JPanel {
	private Iterable<EntityView> entityViews;

	public GravityPanel(){
		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (EntityView view: entityViews){
			Vector startPos = view.getPosition();
			int startX, startY;
			switch (view.getType()){
				case SHIP:
					ShipView shipView = (ShipView) view;
					int size = shipView.getSize();
					startX = (int) (startPos.getX() - size);
					startY = (int) (startPos.getY() - size);
					g2.draw(new Rectangle(startX, startY, size, size));
					break;
				case PLANET:
					PlanetView planetView= (PlanetView) view;
					int radius = (int) planetView.getRadius();
					startX = (int) (startPos.getX() - radius);
					startY = (int) (startPos.getY() - radius);
					g2.fillOval(startX, startY, radius * 2, radius * 2);
					break;
				case NONE:
					break;
			}
			g2.setColor(view.getColor());
		}
	}

	public void paintEntity(EntityView view){

	}

	public void update(Iterable<EntityView> entityViews){
		this.entityViews = entityViews;

	}
}
