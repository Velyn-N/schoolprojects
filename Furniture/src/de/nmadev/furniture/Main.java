package de.nmadev.furniture;

import java.io.*;

public class Main implements Runnable {

	public static void main(String[] args) {
		new Main().run();
	}

	@Override
	public void run() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean end = false;
		
		System.out.println("1 > FurnitureItem (Task one)");
		System.out.println("2 > Table/Seat/Char (Task two)");
		System.out.println("exit > Quit the Application");
		
		do {
			System.out.println("#########################################");
			System.out.print("Your choice: ");
			try {
				String input = in.readLine();

				if (input.startsWith("1")) {
					taskOne();
				} else if (input.startsWith("2")) {
					taskTwo();
				} else if (input.startsWith("exit")) {
					end = true;
				} else {
					System.out.println("Error: Invalid Input!");
				}
			} catch (IOException e) {
			}
		} while (!end);
		System.out.println("Quitting process...");
	}

	private void taskOne() {
		FurnitureItem aItem = new FurnitureItem("Small Table", 2004, 569.99);
		System.out.println(aItem);
		
		FurnitureItem bItem = new FurnitureItem();
		bItem.setName("Round Chair");
		bItem.setYearOfProduction(2003);
		bItem.setPrice(80.0);
		System.out.println(bItem);
	}
	
	private void taskTwo() {
		Table mTable = new Table("Nice", 420, 42.69, 69);
		System.out.println(mTable);
		
		Table nTable = new Table();
		nTable.setName("Hell O'World");
		nTable.setYearOfProduction(666);
		nTable.setPrice(13);
		nTable.setLegs(7);
		System.out.println(nTable);
		
		
		System.out.println("---");
		
		
		Seat aSeat = new Seat("Daniel", 2015, 10.1, false);
		System.out.println(aSeat);
		
		Seat bSeat = new Seat();
		bSeat.setName("The cooler Daniel");
		bSeat.setYearOfProduction(2015);
		bSeat.setPrice(12.3);
		bSeat.setClothCover(true);
		System.out.println(bSeat);
		
		
		System.out.println("---");
		
		
		Chair aChair = new Chair("Claire", 815, 24.7, false, false);
		System.out.println(aChair);
		
		Chair bChair = new Chair();
		bChair.setName("Bear");
		bChair.setYearOfProduction(2010);
		bChair.setPrice(123.45);
		bChair.setClothCover(true);
		bChair.setArmRest(true);
		System.out.println(bChair);
	}
}
