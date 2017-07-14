package  main.java.circulation;

import java.util.List;

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
	
	public void processCommand(String input) {
		
	}
	
	public Copy assignCopyToPatron(int input, BookShelf shelf, Patron patron) {
		Copy c = shelf.getCopies().get(input - 1);
		patron.grabCopy(c);
		shelf.getAvailableCopies().remove(c.getCopyID());
		return c;
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
	public void checkOutCopy(Worker activeWorker, Patron activePatron, Copy copy) {
		activeWorker.checkOut(activePatron, copy);
	}
	public void checkInCopy(Worker activeWorker, Patron activePatron, Copy copy) {
		activeWorker.checkIn(activePatron, copy);
	}
	public List<Copy> checkInPatron(Worker activeWorker, Patron activePatron) {
		return activeWorker.checkIn(activePatron);
	}
	public void identifyPatron(Worker activeWorker, Patron activePatron, boolean result) {
		activeWorker.identifyPatron(activePatron, result);
	}
	public Hold addHold(Worker activeWorker, Patron activePatron, String holdDesc, String copyId) {
		return activeWorker.addHold(activePatron, holdDesc, copyId);
		
	}
	public void removeHold(Worker activeWorker, Patron activePatron, String holdId) {
		activeWorker.removeHold(activePatron, holdId);
		
	}
	
}
