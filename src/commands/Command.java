package commands;

import main.BookInventoryManager;

public abstract class Command {
	public abstract void execute(BookInventoryManager inventory);
	
}
