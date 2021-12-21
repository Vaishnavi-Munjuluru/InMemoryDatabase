package main;
@SuppressWarnings("serial")
public class BookNotFoundException extends Exception {
	BookNotFoundException(String exceptionMessage){
		super(exceptionMessage);
	}
}