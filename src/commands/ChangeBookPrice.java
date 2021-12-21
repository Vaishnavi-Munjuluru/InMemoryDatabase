package commands;
import java.io.Serializable;

import main.BookInventoryManager;
import main.BookNotFoundException;
/**
 * This class executes changePrice operation on inventory.
 */

@SuppressWarnings("serial")
public class ChangeBookPrice extends Command implements Serializable {

	private String bookName;
	private Double price;
	
	public ChangeBookPrice(String givenBookName, Double newPrice){
		this.bookName = givenBookName; 
		this.price = newPrice;
	}
	
	@Override
	public void execute(BookInventoryManager inventory) {
		try {
		    inventory.changeBookPrice(bookName,price);
		} catch (BookNotFoundException exception) {
			exception.printStackTrace();
		}

	}

}
