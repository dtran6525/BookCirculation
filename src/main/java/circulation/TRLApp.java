package main.java.circulation;

import java.util.List;
import java.util.Scanner;

public class TRLApp {
	private Controller controller;
	private Scanner scanner;
	
	public Scanner getScanner() {
		return scanner;
	}

	public TRLApp(Controller controller, Scanner scanner) {
		super();
		this.controller = controller;
		this.scanner = scanner;
	}

	public static void main(String args[]) {
		
		Controller controller = new Controller();
		TRLApp app = new TRLApp(controller, new Scanner(System.in));
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		while (true) {
			app.pickPatron();
			Patron activePatron = controller.pickPatron(app.getScanner().nextInt());
			app.welcomePatron(activePatron);
			boolean isCheckOut = false;
			while (!isCheckOut) {
				app.pickBook(shelf);
				controller.assignCopyToPatron(app.getScanner().nextInt(), shelf, activePatron);
				app.checkoutPrompt(activePatron);
				isCheckOut = controller.getPatronCheckOutPrompt(app.getScanner().next());
			}
			app.pickWorker();
			Worker activeWorker = controller.pickWorker(app.getScanner().nextInt());
			controller.checkOutPatron(activeWorker, activePatron);
			activePatron.leave();
		}
		
	}
	
	public void pickPatron() {
		List<Patron> patrons = FakeDB.getPatrons();
		for (int i = 0 ; i < patrons.size() ; i++) {
			System.out.println( i+1 + ". " + patrons.get(i));
		}
		System.out.print("Pick a patron (Enter a number from 1 - " + patrons.size() + "):");
	}
	
	public void welcomePatron(Patron activePatron) {
		System.out.println("Welcome " + controller.getPatronName(activePatron));
	}
	public void pickBook(BookShelf shelf) {
		List<Copy> copies = shelf.getCopies();
		System.out.println("Here are the all the books we have:");
		for (int i = 0; i < copies.size(); i++) {
			System.out.println(i+1 + ". " + controller.getCopyInfo(copies.get(i)));
		}
		System.out.print("Pick a book (Enter a number from 1 - " + copies.size() + "):");		
	}
	public void checkoutPrompt(Patron activePatron) {
		List<Copy> copies = activePatron.getCopiesCarry();
		System.out.println("You are currently carrying:");
		for (int i = 0; i < copies.size(); i++) {
			System.out.println(i+1 + ". " + controller.getCopyInfo(copies.get(i)));
		}
		System.out.print("Do you want to checkout?");
	}
	public void pickWorker() {
		List<Worker> workers = FakeDB.getWorkers();
		for (int i = 0; i < workers.size(); i++) {
			System.out.println(i+1 + ". " + controller.getWorkerInfo(workers.get(i)));
		}
		System.out.print("Pick a checkout station (Enter a number from 1 - " + workers.size() + "):");
	}
	
}
