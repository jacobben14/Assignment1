package assignment1_jab422;

import java.util.Scanner;
/**
 * A class to handle all user I/O to maintain a hardware store's inventory. 
 * @author Jacob Benavente
 * @version 1.0
 * @since 2/6/2018
 */

public class Main {
	/**
	 * Handles the execution of the program depending on user's input. Once it accepts the input, it directs the user to the proper steps of 
	 * managing the hardware stores' inventory.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		HardwareStore.readingFromFile();
		HardwareStore option = new HardwareStore();
		prompt();
		boolean flag1 = true;
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		while(flag1) {
			switch(choice) {
			case "1": 
				option.DisplayArrList();
				prompt();
				choice = sc.next();
				break;
			case "2":
				option.UpdateQuantity();
				prompt();
				choice = sc.next();
				break;
			case "3":
				option.RemoveQuantity();
				prompt();
				choice = sc.next();
				break;
			case "4":
				option.SearchItem();
				prompt();
				choice = sc.next();
				break;
			case "5":
				option.BelowQuantity();
				prompt();
				choice = sc.next();
				break;
			case "6":
				System.out.print("Saving Data... \nData Saved!");
				HardwareStore.writingToFile();
				flag1 = false;
				break;
			default:
				System.out.print("Please enter a valid option.\n");
				prompt();
				choice = sc.next();
				break;
			}
		}
		sc.close();
	}
	/**
	 * Contains prompt that asks users for their input
	 */
	public static void prompt() {
		System.out.print("Enter the option you would like to do:\n");
		System.out.print("\t1. Show all existing items in stock and their quantities.\n");
		System.out.print("\t2. Add a new quantity of a specific item to the stock.\n");
		System.out.print("\t3. Remove a certain quantity of a specific item type.\n");
		System.out.print("\t4. Search for an item.\n");
		System.out.print("\t5. Show a list of all items below a certain quantity.\n");
		System.out.print("\t6. Exit program.");
		System.out.print("\nYour choice: ");
	}

}
