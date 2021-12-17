package de.nmadev.furniture;

public class Table extends FurnitureItem {

	private int legs;

	public Table() {}

	public Table(String name, int yearOfProduction, double price, int legs) {
		super(name, yearOfProduction, price);
		this.legs = legs;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " \"" + name + "\"(" + yearOfProduction + ") for " + price + " has " + legs + " legs";
	}
}
