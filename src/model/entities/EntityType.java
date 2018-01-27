package model.entities;

public enum EntityType {
	SHIP("ShipView"),
	PLANET("PlanetView"),
	NONE("None");

	String name;

	EntityType(String s){
		name = s;
	}

	public String getName() {
		return name;
	}
}
