package de.nmadev.furniture;

public class Chair extends Seat {

	private boolean armRest;

	public Chair() {}

	public Chair(String name, int yearOfProduction, double price, boolean clothCover, boolean armRest) {
		super(name, yearOfProduction, price, clothCover);
		this.armRest = armRest;
	}

	public boolean hasArmRest() {
		return armRest;
	}

	public void setArmRest(boolean armRest) {
		this.armRest = armRest;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " \"" + name + "\"(" + yearOfProduction + ") for " + price
				+ ((clothCover) ? " with" : " without") + " ClothCover and" 
				+ ((clothCover) ? " with" : " without") + " Armrests";
	}
}
