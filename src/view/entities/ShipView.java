package view.entities;

import model.Vector;
import model.entities.EntityType;
import model.entities.Ship;

import java.awt.*;
import java.util.Random;

public class ShipView implements EntityView {

	public static final int size = 10;

	Ship baseShip;
	Color color;

	public ShipView(Ship baseShip){
		this.baseShip = baseShip;
		Random random = new Random();
		this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
	}

	public ShipView(Ship baseShip, Color color){
		this.baseShip = baseShip;
		this.color = color;
	}

	@Override
	public EntityType getType() {
		return EntityType.SHIP;
	}

	@Override
	public Vector getPosition() {
		return baseShip.getPosition();
	}

	@Override
	public Vector getVelocity() {
		return this.getVelocity();
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public boolean canMove() {
		return baseShip.canMove();
	}

	public int getSize() {
		return size;
	}
}
