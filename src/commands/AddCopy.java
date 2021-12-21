package commands;
import java.io.Serializable;

import main.BookInventoryManager;
import main.BookNotFoundException;
/**
 * This class executes addCopy operation on inventory.
 */

@SuppressWarnings("serial")
public class AddCopy extends Command implements Serializable {

	private String bookName;
	private Integer numberOfCopies;
	
	public AddCopy(String givenBookName, Integer newCopies){
		this.bookName = givenBookName; 
		this.numberOfCopies = newCopies;
	}
	
	@Override
	public void execute(BookInventoryManager inventory) {
		try {
		    inventory.addCopy(bookName,numberOfCopies);
		} catch (BookNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}
