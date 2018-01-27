package view.entities;

import model.Vector;
import model.entities.EntityType;

import java.awt.*;

public interface EntityView{
	public EntityType getType();

	public Vector getPosition();

	public Vector getVelocity();

	public Color getColor();
}
