package view;

import model.Texture;

import java.awt.*;

public class ViewColor {
	static Color BLACK = java.awt.Color.black;
	static Color RED = Color.red;
	static Color BLUE = Color.blue;
	static Color WHITE = Color.white;
	static Color GREEN = Color.green;
	static Color ORANGE = Color.orange;


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
			case GREEN:
				return GREEN;
		}
		throw new IllegalStateException("Error. Unknown color: " + color);
	}
}
