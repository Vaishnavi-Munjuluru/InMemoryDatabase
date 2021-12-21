package main;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import commands.AddBook;
import commands.AddCopy;
import commands.ChangeBookPrice;
import commands.Command;
import commands.SellBook;

/**
 * This class is the decorator for the inventory and has various commands to perform each operation in the inventory.
 */
public class BookInventoryDecorator implements BookInventory {
	
	private BookInventoryManager inventory;
	private Command command;
	private ArrayList<Command> commandCollection;
	private static final String COMMAND_FILE = "command.ser";
	private Memento memento;
	private Object object;
	private FileInputStream inputFile ;
	
	public BookInventoryDecorator(BookInventoryManager invent) {
		this.inventory = invent;
		commandCollection = new ArrayList<Command>();
		memento = new Memento();
	}
	
	/**
	 * sets the state of the current inventory
	 */
	@Override
	public void saveState() {		
		inventory.saveState();
		File file = new File(COMMAND_FILE);
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * It gets the state of the saved inventory.
	 */
	@Override
	public void getState() {
		inventory.getState();
		readCommands(inventory);
	}
	
	public void addBook(Book book) {
		command= new AddBook(book);
		command.execute(inventory);
	    writeCommands();
	}
	
	public void addCopy(String bookName, Integer numberOfCopy) {
		command = new AddCopy(bookName, numberOfCopy);
		command.execute(inventory);	
		writeCommands();
	}
	
	public void changeBookPrice(String bookName, Double newPrice) {
		command = new ChangeBookPrice(bookName, newPrice);
		command.execute(inventory);
		writeCommands();
	}
	
	public void sellBook(String bookName) {
		command = new SellBook(bookName);
		command.execute(inventory);
		writeCommands();
	}
	
	public Double findPriceByName(String bookName) throws BookNotFoundException {
		try {
			return inventory.findPriceByName(bookName);	
		} catch (BookNotFoundException e) {	
			throw new BookNotFoundException("Book Not Found");
		}
	}
	
	public Double findPriceByID(Integer bookId) throws BookNotFoundException {
		try {
			return inventory.findPriceByID(bookId);
		} catch (BookNotFoundException e) {
			throw new BookNotFoundException("Book Not Found");
		}
	}
	
	public Integer findQuantityByName(String bookName) throws BookNotFoundException {
		try {
			return inventory.findQuantityByName(bookName);
		} catch (BookNotFoundException e) {
			throw new BookNotFoundException("Book Not Found");
		}
	}
	
	public Integer findQuantityByID(Integer bookId) throws BookNotFoundException {
		try { 
			return inventory.findQuantityByID(bookId);
		} catch (BookNotFoundException e) {
			throw new BookNotFoundException("Book Not Found");
		}
	}
	
	/**
	 * This method writes the command to the file to serialize the command
	 */
	private void writeCommands() {
		try {
	         FileOutputStream outputFile = new FileOutputStream(COMMAND_FILE,true);
	         ObjectOutputStream out = new ObjectOutputStream(outputFile);
	         out.writeObject(command);
	         out.close();
	         outputFile.close();
	      } catch(IOException exception) {
	    	  exception.printStackTrace();
	      }
	}
	
	/**
	 * This method get the commands from file (deserializes the command) 
	 * and also runs them to inventory object       
	 */
	private void readCommands(BookInventoryManager invent) {
		try {
			inputFile = new FileInputStream(COMMAND_FILE);
			while (true) {
			ObjectInputStream input = new ObjectInputStream(inputFile);
		    commandCollection.add((Command) input.readObject());
		  }  
		} catch (EOFException e) {
			try {	
				inputFile.close();
			}catch(IOException exception) {
				exception.printStackTrace();
			}
		} catch(IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		for(Command command : commandCollection) {
	         command.execute(invent);
		}
	}
	
}