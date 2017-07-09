package main.java.circulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class TRLApp {
	public static void main(String args[]) {
		
		BookShelf shelf = new BookShelf();
		
		shelf.putCopies(FakeDB.getCopies());
	
		Scanner sc = new Scanner(System.in);
		
		
		
		List<Patron> patrons = FakeDB.getPatrons();
		for (int i = 0 ; i < patrons.size() ; i++) {
			System.out.println( i+1 + ". " + patrons.get(i));
		}
				
		System.out.println("Pick a patron (Enter a number from 1 - " + patrons.size() + "):");
		
		
		System.out.println("Welcome");
		
		
		
		p1.grabCopy(shelf.passCopy("c1"));
		p1.grabCopy(shelf.passCopy("c2"));
		p2.grabCopy(shelf.passCopy("c3"));
		p2.grabCopy(shelf.passCopy("c4"));
		p2.grabCopy(shelf.passCopy("c5"));
		p3.grabCopy(shelf.passCopy("c6"));
		
		Worker w1 = FakeDB.getWorker("w1");
		Worker w2 = FakeDB.getWorker("w2");
		
		w1.checkOut(p1);
		w1.checkOut(p2);
		w2.checkOut(p3);
	}
}
