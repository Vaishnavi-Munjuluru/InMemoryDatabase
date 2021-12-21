package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
/**
 * This class used to save/get state of inventory
 */
 
@SuppressWarnings("serial") 
class Memento implements Serializable{

	protected HashMap<String, Book> bookCollection;
	
	protected void saveBookCollection(HashMap<String, Book> newBookCollection){
		this.bookCollection = new HashMap<String, Book>(newBookCollection);	
	}
	
	protected HashMap<String, Book> getBookCollection(){
		return this.bookCollection;
	}
}
