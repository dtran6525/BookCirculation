package main.java.circulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TRLApp {
	private Controller controller;
	private Scanner scanner;
	private BookShelf shelf;
	private Patron activePatron;
	private Worker activeWorker;

	public Patron getActivePatron() {
		return activePatron;
	}

	public void setActivePatron(Patron activePatron) {
		this.activePatron = activePatron;
	}

	public Worker getActiveWorker() {
		return activeWorker;
	}

	public void setActiveWorker(Worker activeWorker) {
		this.activeWorker = activeWorker;
	}

	public BookShelf getShelf() {
		return shelf;
	}

	public void setShelf(BookShelf shelf) {
		this.shelf = shelf;
	}

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
		app.setShelf(shelf);
		while (true) {
			app.pickPatron();
			Patron activePatron = controller.pickPatron(app.getScanner().nextInt());
			app.setActivePatron(activePatron);
			app.welcomePatron();
			
			boolean leave = false;
			while (!leave) {
				System.out.println("======> You are now controlling "+ activePatron);
				System.out.println("Available commands: status, shelf, checkout, checkin, leave");
				System.out.print("What do you want to do:");
				String command = app.getScanner().next();
				if (command.equalsIgnoreCase("status")) {
					app.displayPatronStatus();
				} else if (command.equalsIgnoreCase("shelf")) {
					app.pickBook();
					controller.assignCopyToPatron(app.getScanner().nextInt(), shelf, activePatron);
				} else if (command.equalsIgnoreCase("checkout")) {
					app.pickWorker();
					Worker activeWorker = controller.pickWorker(app.getScanner().nextInt());
					app.setActiveWorker(activeWorker);
					boolean workerDone = true;
					while (workerDone) {
						System.out.println("======> You are now controlling "+ activeWorker );
						System.out.println("======> You are now checking out "+ activePatron );
						System.out.println("Available commands: id, copies, holds, done");
						System.out.print("What do you want to do:");
						String workerCommand = app.getScanner().next();
						if (workerCommand.equalsIgnoreCase("id")) {
							app.identifyPatron();
						} else if (workerCommand.equalsIgnoreCase("copies")) {
							List<Copy> copiesCarry = activePatron.getCopiesCarry();
							for (int i = copiesCarry.size() - 1; i >= 0; i--) {
								Copy activeCopy = copiesCarry.get(i);
								app.displayCopy(activeCopy);
								if (app.verifyCopy()) controller.checkOutCopy(activeWorker, activePatron, activeCopy);
								else System.out.println("Unable to verify " + activeCopy);
							}
						} else if (workerCommand.equalsIgnoreCase("holds")) {
							boolean holdDone = true;
							while (holdDone) {
								System.out.println("======> Holds Management");
								System.out.println("Available commands: browse, add, remove, done");
								System.out.print("What do you want to do:");
								String holdCommand = app.getScanner().next();
								if (holdCommand.equals("browse")) {
									app.browseHolds();
								} else if (holdCommand.equals("add")) {
									app.addHold();
								} else if (holdCommand.equals("remove")) {
									app.removeHold();
								} else if (holdCommand.equals("done")) {
									holdDone = false;
								}
							}
							
						} else if (workerCommand.equalsIgnoreCase("done")) {
							System.out.println("Thank you and have a nice day!");
							workerDone = false;
						}
					}
					
				} else if (command.equalsIgnoreCase("checkin")) {
					app.pickWorker();
					Worker activeWorker = controller.pickWorker(app.getScanner().nextInt());
					System.out.println(activeWorker + " is checking in " + activePatron);
					app.setActiveWorker(activeWorker);
					boolean workerDone = true;
					while (workerDone) {
						System.out.println("======> You are now controlling "+ activeWorker);
						System.out.println("======> You are now checking in "+ activePatron );
						System.out.println("Available commands: id, copies, holds, done");
						System.out.print("What do you want to do:");
						String workerCommand = app.getScanner().next();
						if (workerCommand.equalsIgnoreCase("id")) {
							app.identifyPatron();
						} else if (workerCommand.equalsIgnoreCase("copies")) {
							List<Copy> checkedInCopies = new ArrayList<>();
							List<Copy> copiesOut = activePatron.getCopiesOut();
							for (int i = copiesOut.size() - 1; i >= 0; i--) {
								Copy activeCopy = copiesOut.get(i);
								app.displayCopy(activeCopy);
								if (app.verifyCopy()) {
									controller.checkInCopy(activeWorker, activePatron, activeCopy);
									checkedInCopies.add(activeCopy);
								} else {
									System.out.println("Unable to verify " + activeCopy);
								}
							}
							shelf.putCopies(checkedInCopies);
							
						} else if (workerCommand.equalsIgnoreCase("holds")) {
							boolean holdDone = true;
							while (holdDone) {
								System.out.println("======> Holds Management");
								System.out.println("Available commands: browse, add, remove, done");
								System.out.print("What do you want to do:");
								String holdCommand = app.getScanner().next();
								if (holdCommand.equals("browse")) {
									app.browseHolds();
								} else if (holdCommand.equals("add")) {
									app.addHold();
								} else if (holdCommand.equals("remove")) {
									app.removeHold();
								} else if (holdCommand.equals("done")) {
									holdDone = false;
								}
							}
							
						} else if (workerCommand.equalsIgnoreCase("done")) {
							System.out.println("Thank you and have a nice day!");
							workerDone = false;
						}
					}
					
				} else if (command.equalsIgnoreCase("leave")) {
					activePatron.leave();
					leave = true;
				} 
			}
		}
		
	}

	private void browseHolds() {
		Service service = new Service();
		List<Hold> holds = service.getHolds(activePatron.getPatronID());
		for (Hold h : holds) {
			System.out.println(h);
		}
	}

	private void removeHold() {
		System.out.println("Enter hold id:");
		controller.removeHold(activeWorker, activePatron, scanner.next());
	}

	private void addHold() {
		System.out.print("Enter description:");
		String holdDesc = scanner.next();
		holdDesc += scanner.nextLine();
		System.out.print("Enter copy id(optional) :");
		String copyId = scanner.next();
		controller.addHold(activeWorker, activePatron, holdDesc, copyId);
	}

	public void pickPatron() {
		List<Patron> patrons = FakeDB.getPatrons();
		for (int i = 0; i < patrons.size(); i++) {
			System.out.println(i + 1 + ". " + patrons.get(i));
		}
		System.out.print("Pick a patron (Enter a number from 1 - " + patrons.size() + "):");
	}

	public void welcomePatron() {
		System.out.println("Welcome " + controller.getPatronName(activePatron));
	}

	public void pickBook() {
		List<Copy> copies = shelf.getCopies();
		System.out.println("Here are the all the books we have:");
		for (int i = 0; i < copies.size(); i++) {
			System.out.println(i + 1 + ". " + controller.getCopyInfo(copies.get(i)));
		}
		System.out.print("Pick a book (Enter a number from 1 - " + copies.size() + "):");
	}

	public void displayPatronStatus() {
		List<Copy> copiesCarry = activePatron.getCopiesCarry();
		System.out.println("Carrying: " + copiesCarry);
		List<Copy> copiesOut = activePatron.getCopiesOut();
		System.out.println("Checked-out: " + copiesOut);
		List<Hold> holds = new Service().getHolds(activePatron.getPatronID());
		System.out.println("Holds: " + holds);
	}

	public void displayCopiesCarry() {
		List<Copy> copies = activePatron.getCopiesCarry();
		System.out.println( activePatron + "currently carrying:");
		for (int i = 0; i < copies.size(); i++) {
			System.out.println(i + 1 + ". " + controller.getCopyInfo(copies.get(i)));
		}
	}

	public void pickWorker() {
		List<Worker> workers = FakeDB.getWorkers();
		for (int i = 0; i < workers.size(); i++) {
			System.out.println(i + 1 + ". " + controller.getWorkerInfo(workers.get(i)));
		}
		System.out.print("Pick a checkout station (Enter a number from 1 - " + workers.size() + "):");
	}

	public void identifyPatron() {
		Service service = new Service();
		System.out.print("Enter patron id:");
		Patron verify = service.lookupPatron(scanner.next());
		System.out.print("Please verify that current patron is " + verify + "(y/n)");
		boolean result =  scanner.next().equalsIgnoreCase("y");
		controller.identifyPatron(activeWorker, activePatron, result);
		if (result && verify != null && service.hasHolds(verify.getPatronID())) {
			System.out.println("Warning! " + verify + " has the following holds: ");
			for (Hold h : service.getHolds(verify.getPatronID())) {
				System.out.println(h);
			}
		}
	}

	public void displayCopy(Copy currentCopy) {
		System.out.println("Current copy is " + currentCopy);
	}
	
	public boolean verifyCopy() {
		Service service = new Service();
		System.out.print("Enter copy id:");
		Copy verify = service.lookupCopy(scanner.next());
		System.out.print("Please verify that current copy is " + verify + "(y/n):");
		return scanner.next().equalsIgnoreCase("y");
	}
	

}
