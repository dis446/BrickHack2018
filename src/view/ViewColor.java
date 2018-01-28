package view;

import java.awt.*;

public class ViewColor {
	public static Color BLACK = java.awt.Color.black;
	public static Color RED = java.awt.Color.red;

	public static Color modelToAWTColor(model.Color color){
		switch (color){
			case BLACK:
				return BLACK;
			case RED:
				return RED;
		}
		throw new IllegalStateException("Error. Unknown color: " + color);
	}
}
