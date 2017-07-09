package  main.java.circulation;

public class Controller {
	
	public Patron pickPatron(int input) {
		return FakeDB.getPatrons().get(input - 1);
	}
	public String getPatronName(Patron patron) {
		return patron.getName();
	}
	public String getCopyInfo(Copy copy) {
		return copy.toString();
	}
	public void assignCopyToPatron(int input, BookShelf shelf, Patron patron) {
		Copy c = shelf.getCopies().get(input - 1);
		patron.grabCopy(c);
		shelf.getAvailableCopies().remove(c.getCopyID());
	}
	public boolean getPatronCheckOutPrompt(String input) {
		return input.equalsIgnoreCase("y");
	}
	public String getWorkerInfo(Worker worker) {
		return worker.toString();
	}
	public Worker pickWorker(int input) {
		return FakeDB.getWorkers().get(input - 1);
	}
	public void checkOutPatron(Worker activeWorker, Patron activePatron) {
		activeWorker.checkOut(activePatron);
	}
	
}
