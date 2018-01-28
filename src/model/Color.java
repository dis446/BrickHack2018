package model;

public enum Color {
	BLACK("BLACK") , RED("RED"), WHITE("WHITE"), BLUE("BLUE"), ORANGE("ORANGE");

	private String label;

	Color(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static Color parseColor(String s){
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
		}
		throw new IllegalStateException("Error. Unknown color: " + s);
	}
}
