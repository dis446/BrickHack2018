package model;

public enum Texture {
	BLACK("BLACK") , RED("RED"), WHITE("WHITE"), BLUE("BLUE"), ORANGE("ORANGE"), GREEN("GREEN");

	private String label;

	Texture(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static Texture parseColor(String s){
		switch (s){
			case "BLACK":
				return BLACK;
			case "RED":
				return RED;
			case "WHITE":
				return WHITE;
			case "BLUE":
				return BLUE;
			case "ORANGE":
				return ORANGE;
			case "GREEN":
				return GREEN;
		}
		throw new IllegalStateException("Error. Unknown color: " + s);
	}
}
