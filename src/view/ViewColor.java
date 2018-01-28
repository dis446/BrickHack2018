package view;

import model.Texture;

import java.awt.*;

public class ViewColor {
	public static Color BLACK = java.awt.Color.black;
	public static Color RED = Color.red;
	public static Color BLUE = Color.blue;
	public static Color WHITE = Color.white;
	public static Color ORANGE = Color.orange;

	public static Color modelToAWTColor(Texture color){
		switch (color){
			case BLACK:
				return BLACK;
			case RED:
				return RED;
			case WHITE:
				return WHITE;
			case BLUE:
				return BLUE;
			case ORANGE:
				return ORANGE;
		}
		throw new IllegalStateException("Error. Unknown color: " + color);
	}
}
