package main;
import java.io.Serializable;

/**
*
* This class has the properties of each book and their getter setter methods to invoke the required variable.
*/
public class Book implements Serializable{

	private Double  price;
	private Integer uniqueId, quantity;
	private String name;
	private static int idGenerator = 1;
	
	public Book(String newName, Double newPrice, Integer newQuantity ){
		this.name = newName;
		this.price = newPrice;
		this.uniqueId = idGenerator++;
		this.quantity = newQuantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getUniqueID() {
		return uniqueId;
	}

	public void setUniqueID(Integer uniqueID) {
		this.uniqueId = uniqueID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public void changeQuantity(int change){
		this.quantity += change;
	}
	
}
