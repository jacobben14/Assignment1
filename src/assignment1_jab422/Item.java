package assignment1_jab422;

/**
 * Creates item of what the hardware store's inventory consists of.
 * @author Jacob Benavente
 * @version 1.0
 * @since 2/6/2018
 */
public class Item {
	
	String idNumber;
	String name;
	String category;
	int quantity;
	float price;
	/**
	 * Default constructor
	 */
	public Item() {
		
	}
	/**
	 * Constructor to set values
	 * @param idNumber
	 * @param name
	 * @param category
	 * @param quantity
	 * @param price
	 */
	public Item(String idNumber, String name, String category, int quantity, float price){
		this.idNumber = idNumber;
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return idNumber+"~"+name+"~"+category+"~"+quantity+"~"+price;
	}
 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIdNumber() {return idNumber;}
	public String getName() {return name;}
	public String getCategory() {return category;}
	public int getQuantity() {return quantity;}
	public float getPrice() {return price;}
}
