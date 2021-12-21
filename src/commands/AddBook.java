package commands;
import java.io.Serializable;

import main.Book;
import main.BookInventoryManager;

/**
 * This class executes addBook operation on inventory.
 */

@SuppressWarnings("serial")
public class AddBook extends Command implements Serializable {
	private Book book;
	
	public AddBook(Book givenBook){
		this.book = givenBook; 
	}
	
	@Override
	public void execute(BookInventoryManager inventory) {
		inventory.addBook(book);
	}
}
