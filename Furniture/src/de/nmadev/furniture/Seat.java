package de.nmadev.furniture;

public class Seat extends FurnitureItem {

	protected boolean clothCover;

	public Seat() {}

	public Seat(String name, int yearOfProduction, double price, boolean clothCover) {
		super(name, yearOfProduction, price);
		this.clothCover = clothCover;
	}

	public boolean hasClothCover() {
		return clothCover;
	}

	public void setClothCover(boolean clothCover) {
		this.clothCover = clothCover;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " \"" + name + "\"(" + yearOfProduction + ") for " + price 
				+ ((clothCover) ? " with " : " without ") + " ClothCover";
	}
}
