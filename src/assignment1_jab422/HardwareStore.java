package assignment1_jab422;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class is the hardware store's main support system. It runs the backend calculation, searches, updates, etc. depending on user's input.
 * @author Jacob Benavente
 * @version 1.0
 * @since 2/6/2018
 *
 */
public class HardwareStore {
	
	static ArrayList<Item> inventory = new ArrayList<Item>();
	Item itemMan = new Item();
	Scanner sc = new Scanner(System.in);
	String choice;
	int quanChoice = 0;
	float priceHolder = 0;
	/**
	 * Reads from the file
	 * @throws IOException
	 */
	public static void readingFromFile() throws IOException{
		
		try {
			File myFile = new File("database.txt");
			
			if(myFile.createNewFile()) {
				System.out.println("File created!");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
				
		FileReader fr = new FileReader("database.txt");
		Scanner inFile = new Scanner(fr);
		while(inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String[] words = line.split("~");
			String idNumber = words[0];
			String name = words[1];
			String category = words[2];
			int quantity = Integer.parseInt(words[3]);
			float price = Float.parseFloat(words[4]);
			Item holder = new Item(idNumber, name, category, quantity, price);
			inventory.add(holder);
			//System.out.println("Writing to arraylist: "+ holder);
			//System.out.println("inventory: " + inventory);
		}
		
		
		inFile.close();
	}
	/**
	 * Writes to the file
	 * @throws IOException
	 */
	public static void writingToFile() throws IOException{
		
		FileWriter fw = new FileWriter("database.txt");
		PrintWriter outFile = new PrintWriter(fw);
		for(Item itemMan: inventory) {
			outFile.println(itemMan);
		}
		outFile.close();
	}
	/**
	 * Updates the quantity amount (+) depending on user's input
	 */
	public void UpdateQuantity() {
		System.out.print("Enter an ID number (Max 5 character): ");
		choice = sc.next();
		int holder = 0;
		boolean flag1 = false;
		for(Item itemMan:inventory) {
			if(itemMan.getIdNumber().equals(choice)) {
				System.out.print("Enter the amount to add to quantity: ");
				while(!sc.hasNextInt()) {
					System.out.println("Please enter a valid input");
					sc.next();
				}
				quanChoice = sc.nextInt();
				while(quanChoice < 0) {
					System.out.println("Please enter a positive number. ");
					while(!sc.hasNextInt()) {
						System.out.println("Please enter a valid input");
						sc.next();
					}
					quanChoice = sc.nextInt();
				}
				holder = itemMan.getQuantity();
				holder = holder + quanChoice;
				itemMan.setQuantity(holder);
				quanChoice = 0;
				flag1 = true;
			}
		}
		if(!flag1) {AddNewItem();}
		flag1 = false;
	}

	/**
	 * If the user wants to update a quantity amount for an item that does not exist then the 
	 * user will be asked to create a new item.
	 */
	public void AddNewItem() {
		Item itemMan = new Item();
		System.out.println("\nItem ID not found. Please create new item.\n\nEnter new ID Number: ");
		String idNumber = sc.next();
		while(idNumber.length() != 5) {
			System.out.print("Please enter a 5 character ID Number: ");
			idNumber = sc.next();
		}
		itemMan.setIdNumber(idNumber);
//NOTE: Couldn't figure out how to enter two words. When entered two words it complete skips the category section.
//		Tried nextLine instead of next but also ran into problems with that.
		System.out.println("Enter new Name for item: ");
		choice = sc.next();
		itemMan.setName(choice);
		System.out.println("Please select category this item falls under.");
		System.out.println("\tA. Door&Window\n\tB. Cabinet&Furniture\n\tC. Fasteners\n\tD. Structural\n\tE. Other");
		choice = sc.next();
//NOTE: Couldn't figure out how to do a user validation for the selection of category.
//		If the user entered anything but the asked options it will ask them to enter a valid option and reenter something
//      else, and then jump to quantity amount.
			switch(choice) {
			case "A":
			case "a":
				itemMan.setCategory("Door&Window");
				break;
			case "B":
			case "b":
				itemMan.setCategory("Cabinet&Furniture");
				break;
			case "C":
			case "c":
				itemMan.setCategory("Fasteners");
				break;
			case "D":
			case "d":
				itemMan.setCategory("Structural");
				break;
			case "E":
			case "e":
				itemMan.setCategory("Other");
				break;
			default:
				System.out.println("Please enter a valid option.");
				choice = sc.next();
				break;
			}
		System.out.println("Enter quantity amount: ");
		while(!sc.hasNextInt()) {
			System.out.println("Please enter a valid input.");
			sc.next();
		}
		quanChoice = sc.nextInt();
		while(quanChoice < 0) {
			System.out.println("Please enter a positive number. ");
			while(!sc.hasNextInt()) {
				System.out.println("Please enter a valid input. ");
				sc.next();
			}
			quanChoice = sc.nextInt();
		}
		itemMan.setQuantity(quanChoice);
		System.out.println("Enter price: $");
		while(!sc.hasNextFloat()) {
			System.out.println("Please enter a valid input.");
			sc.next();
		}
		priceHolder = sc.nextFloat();
		while(priceHolder < 0) {
			System.out.print("Please enter a positive number. \n");
			while(!sc.hasNextFloat()) {
				System.out.println("Please enter a valid input. ");
				sc.next();
			}
			priceHolder = sc.nextFloat();
		}
		itemMan.setPrice(priceHolder);
		inventory.add(itemMan);
	}

	/**

	 * Displays the contents of the ArrayList
	 */
	public void DisplayArrList() {
		System.out.println(" ---------------------------------------------------------------------------------------");
		System.out.format("%8s%25s%25s%15s%16s","|  "+"ID#|","NAME|","CATEGORY|","QUANTITY|","PRICE|\n");
		System.out.println(" ---------------------------------------------------------------------------------------");
		for(Item itemMan:inventory) {
			System.out.format("%8s%25s%25s%15s%15s","|"+itemMan.getIdNumber()+"|",itemMan.getName()+"|",itemMan.getCategory()+"|",itemMan.getQuantity()+"|","$"+itemMan.getPrice()+"|");
			System.out.print("\n");
		}
		System.out.println(" ---------------------------------------------------------------------------------------\n");
	}

	/**
	 * Updates the quantity amount (-) depending on user's input
	 */
	public void RemoveQuantity() {
		System.out.print("Enter ID Number of item you want to remove quantity: ");
		choice = sc.next();
		boolean flag = false;
		
		for(Item itemMan:inventory) {
			if(itemMan.getIdNumber().equals(choice)) {
				System.out.print("Enter amount you want to remove: ");
				while(!sc.hasNextInt()) {
					System.out.println("Please enter a valid input");
					sc.next();
				}
				quanChoice = sc.nextInt();
				while(quanChoice > itemMan.getQuantity()) {
					System.out.println("Overload. Please enter a proper amount. ");
					quanChoice = sc.nextInt();
				}
				while(quanChoice < 0) {
					System.out.println("Please enter a positive number. ");
					while(!sc.hasNextInt()) {
						System.out.println("Please enter a valid input");
						sc.next();
					}
					quanChoice = sc.nextInt();
				}
				int holder = itemMan.getQuantity();
				holder = holder - quanChoice;
				itemMan.setQuantity(holder);
				quanChoice = 0;
				flag = true;
			}
		}
		if(!flag) {System.out.println("Item does not exist");}
		flag = false;
	}
	/**
	 * Displays the items that have a quantity below what the user inputs.
	 */
	public void BelowQuantity() {
		System.out.println("Please enter quantity amount: ");
		while(!sc.hasNextInt()) {
			System.out.println("Please enter a valid input");
			sc.next();
		}
		quanChoice = sc.nextInt();
		boolean flag = false;
		for(Item itemMan:inventory) {
			if(itemMan.getQuantity() < quanChoice) {
				System.out.println(itemMan.getName()+" is under the amount " + quanChoice + " at " + itemMan.getQuantity());
				flag = true;
			}
		}
		if(!flag) {System.out.print("No items have quantity under "+quanChoice+".\n");}
	}
	/**
	 * Searches for a name/part of name that the user inputs. 
	 */
	public void SearchItem() {
		System.out.print("Enter the name or part of the name of the item you're looking for: ");
		choice = sc.next();
		boolean flag = false;
		
		for(Item itemMan:inventory) {
			if(itemMan.getName().contains(choice)) {
				System.out.print("Items with the name or similar to the name "+choice+" are: ");
				System.out.println(itemMan.getName()+" - "+itemMan.getQuantity()+" $"+itemMan.getPrice());
				flag = true;
			}
		}
		if(!flag) {System.out.println("Name cannot be found.");}
	}
}


