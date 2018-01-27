package view.entities;

import model.Vector;
import model.entities.EntityType;

import java.awt.*;

public interface EntityView{
	EntityType getType();

	Vector getPosition();

	Vector getVelocity();

	Color getColor();

	boolean canMove();
}
