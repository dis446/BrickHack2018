package model;

public enum Color {
	BLACK("black") , RED("red");

	private String label;

	Color(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static Color parseColor(String s){
		switch (s){
			case "black":
				return BLACK;
			case "red":
				return RED;
		}
		throw new IllegalStateException("Error. Unknown color: " + s);
	}
}
