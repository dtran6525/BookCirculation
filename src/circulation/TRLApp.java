package circulation;

import java.util.ArrayList;
import java.util.List;

public class TRLApp {
	public List<Patron> patrons = new ArrayList<>();
	public List<Worker> workers = new ArrayList<>();
	public List<Copy> availableCopies = new ArrayList<>();
	
	public static void main(String argsp[]) {
		Patron p1 = new Patron("p1", "John");
		Patron p2 = new Patron("p2", "James");
		Patron p3 = new Patron("p3", "Jill");
		Worker w1 = new Worker("w1", "Derek");
		Worker w2 = new Worker("w2", "Jake");
		Textbook b1 = new Textbook("b1", "Programming in Java");
		Textbook b2 = new Textbook("b2", "Programming in Python");
		Textbook b3 = new Textbook("b3", "Programming in Scala");
		
		
		System.out.println("Welcome " + p1.getName());
		
	}
}
