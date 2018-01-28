package model.entities;

public enum EntityType {
	SHIP("Ship"),
	PLANET("Planet"),
	NONE("None");

	String name;

	EntityType(String s){
		name = s;
	}

	public String getName() {
		return name;
	}
}
