package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Book;
import main.BookInventory;
import main.BookInventoryDecorator;
import main.BookInventoryManager;
import main.BookNotFoundException;

class BookInventoryDecoratorTest {

	public BookInventory inventory;
	
	 @BeforeEach 
	 void init() throws Exception {
		 inventory = new BookInventoryDecorator(new BookInventoryManager());
		 inventory.addBook(new Book("Secret", (double) 1000, 10));
		 inventory.addBook(new Book("The Alchemist", (double) 1200, 5));
		 inventory.addBook(new Book("GOF", (double) 1500, 15));
		 inventory.addBook(new Book("Design Patterns", (double) 2000, 25));
		 inventory.addBook(new Book("OOPS", (double) 1000, 50));
		 inventory.addBook(new Book("DataBases", (double) 800, 15));
		 inventory.addBook(new Book("InMemoryDB", (double) 200, 25));
		 inventory.addBook(new Book("Persistence", (double) 3000, 35));
		 inventory.addBook(new Book("HeadFirst Java", (double) 1200, 5));
		 inventory.addBook(new Book("HeadFirst DP", (double) 400, 10));
		 inventory.addBook(new Book("Data Structures", (double) 800, 20));
		 inventory.addBook(new Book("Data science", (double) 600, 45));
   }

	@Test
	void testAddBook() throws BookNotFoundException {
		inventory.addBook(new Book("The Immortals of Meluha", (double) 1200, 5));
		assertEquals(inventory.findPriceByName("The Immortals of Meluha"), 1200);
	}

	@Test
	void testAddCopy() throws BookNotFoundException{
		inventory.addCopy("Secret", 20);
		assertEquals(inventory.findQuantityByName("Secret"), 30);
	}

	@Test
	void testChangePrice() throws Exception {
		inventory.changeBookPrice("Secret", (double) 2000);
		assertEquals(inventory.findPriceByName("Secret"), 2000);
	}

	@Test
	void testSellBook() throws BookNotFoundException {
		inventory.sellBook("The Alchemist");
		assertEquals(inventory.findQuantityByName("The Alchemist"), 4);
	}
	
	@Test 
	void testGetState() throws BookNotFoundException {
		BookInventory invent = new BookInventoryDecorator(new BookInventoryManager());
	    invent.getState();
	    assertEquals(invent.findQuantityByName("HeadFirst Java"), 5);
	}
	
	@Test 
	void testSaveState() throws BookNotFoundException {
		BookInventory invent = new BookInventoryDecorator(new BookInventoryManager());
		invent.addBook(new Book("Head First Java", (double) 500, 20));
	    invent.saveState();
	    BookInventory inventUpdated = new BookInventoryDecorator(new BookInventoryManager());
	    inventUpdated.getState();
	    assertEquals(inventUpdated.findQuantityByName("Head First Java"), 20);
	}

	@Test
	void testQuantityById() throws BookNotFoundException{
		assertEquals(inventory.findQuantityByID(2), 5);
	}
	

}
