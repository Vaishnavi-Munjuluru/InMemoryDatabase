package main;
import java.util.HashMap;

/**
 * This is the inventory manager where the actual operations of add, sell and change is performed.
 */
public class BookInventoryManager implements BookInventory {

	private static final Integer SAVE_NOW = 10;
	private HashMap<String, Book> bookCollection;
	private Memento memento;
	private MementoSerializer careTaker;
	private Integer numberOfOperations;

	public BookInventoryManager() {
		bookCollection = new HashMap<String, Book>();
		memento = new Memento();
		numberOfOperations = 0;
		careTaker = new MementoSerializer();
	}

	/**
	 * This method saves the state of inventory to file and serializes that inventory state.    
	 */
	public void saveState() {
	    memento.saveBookCollection(bookCollection);
	    careTaker.serialzeMemento(memento);
	}
	
	/**
	 * This method get the previous state of inventory from file by deserializing the file      
	 */
	public void getState() {
		memento  = careTaker.deserialseMemento();
		bookCollection = memento.getBookCollection();
	}
	
	public void addBook(Book book){
		bookCollection.put(book.getName(),book);
		if(++numberOfOperations == SAVE_NOW){
	    	this.saveState();
	    	numberOfOperations=0;
	    }	
	}
	
	public void sellBook(String bookName) throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName) && book.getQuantity() > 0){
				book.changeQuantity(-1);
				if(++numberOfOperations == SAVE_NOW){
			    	this.saveState();
			    	numberOfOperations=0;
			    }
				return ;
			}	
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public void addCopy(String bookName, Integer NumberOfCopy )throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				book.changeQuantity(NumberOfCopy);
				if(++numberOfOperations == SAVE_NOW){
			    	this.saveState();
			    	numberOfOperations=0;
			    }
				return ;	
			}
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public void changeBookPrice(String bookName,Double newPrice)throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				book.setPrice(newPrice);
				if(++numberOfOperations == SAVE_NOW){
			    	this.saveState();
			    	numberOfOperations=0;
			    }
				return ;
			}
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public Double findPriceByName(String bookName) throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				return book.getPrice();
			}
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public Integer findQuantityByName(String bookName) throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getName().equals(bookName)){
				return book.getQuantity();
			}	
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public Integer findQuantityByID(Integer bookId) throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getUniqueID().equals(bookId)){
				return book.getQuantity();
			}	
		}
		throw new BookNotFoundException("Book not Found");
	}
	
	public Double findPriceByID(Integer bookId) throws BookNotFoundException {
		for(Book book : bookCollection.values()){
			if(book.getUniqueID().equals(bookId)){
				return book.getPrice();
			}
		}
		throw new BookNotFoundException("Book not Found");
	}
}