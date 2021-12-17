package de.nmadev.furniture;

public class FurnitureItem {

	protected String name;
	protected int yearOfProduction;
	protected double price;

	public FurnitureItem() {
	}

	public FurnitureItem(String name, int yearOfProduction, double price) {
		this.name = name;
		this.yearOfProduction = yearOfProduction;
		this.price = price;
	}


	public double getSellingPrice() {
		return price * 1.15;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " \"" + name + "\"(" + yearOfProduction + ") for " + price;
	}
}
