package main;
import java.io.*;

/**
 * This class to serialize and deserialize Memento to/from file.       
 */

public class MementoSerializer {
	private Object object;
	private static final String INVENTORY_FILE = "inventory.ser";
 
	/**
	 * this method writes the given memento to the inventory.ser file.
	 * @param memento
	 */
	public void serialzeMemento(Memento memento){
		try {
			FileOutputStream fileOut = new FileOutputStream(INVENTORY_FILE);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(memento);         
	        out.close();
	        fileOut.close();
	    } catch(IOException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	
	/**
	 * This method returns the memento which is saved.
	 * @return memento read from inventory.ser file
	 */
   public Memento deserialseMemento(){	
	   try {
		   FileInputStream fileIn = new FileInputStream(INVENTORY_FILE);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       object = in.readObject();
	       in.close();
	       fileIn.close();
           return (Memento)object;
	   } catch(IOException exception) {
		   exception.printStackTrace();
	       return null;  
	   } catch(ClassNotFoundException exception) {
		   exception.printStackTrace();
	       return null;
	   }   
   }
   
}