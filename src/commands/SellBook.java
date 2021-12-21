package commands;
import java.io.Serializable;

import main.BookInventoryManager;
import main.BookNotFoundException;
/**
 * This class executes sellBook operation on inventory.
 */

@SuppressWarnings("serial")
public class SellBook extends Command implements Serializable {

	private String bookName;
	
	public SellBook(String givenBookName){
		this.bookName = givenBookName; 
	}
	
	@Override
	public void execute(BookInventoryManager inventory) {
		
		try {
			  inventory.sellBook(bookName);
		} catch (BookNotFoundException e) {
			System.out.println(e);
		}
	}

}
