package main;
public interface BookInventory {
	public void saveState();
	public void getState();
	public void addBook(Book book);
	public void sellBook(String bookName)throws BookNotFoundException;
	public void addCopy(String bookName, Integer numberOfCopies ) throws BookNotFoundException;
	public void changeBookPrice(String bookName,Double newPrice) throws BookNotFoundException;
	public Double findPriceByName(String bookName) throws BookNotFoundException;
	public Double findPriceByID(Integer bookId) throws BookNotFoundException;
	public Integer findQuantityByName(String bookName) throws BookNotFoundException;
	public Integer findQuantityByID(Integer bookId) throws BookNotFoundException;
}
