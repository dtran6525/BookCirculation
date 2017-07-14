package test.java.circulation;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import main.java.circulation.BookShelf;
import main.java.circulation.Controller;
import main.java.circulation.Copy;
import main.java.circulation.FakeDB;
import main.java.circulation.Hold;
import main.java.circulation.Patron;
import main.java.circulation.Service;
import main.java.circulation.Worker;



public class PatronTest
{
	
	@After
	public void cleanUpPatron() {
		Patron p1 = FakeDB.getPatron("p1");
		p1.getCopiesCarry().clear();
		p1.getCopiesOut().clear();
	}
	
	@Test
	public void putCopyOnShelfTest() {
		Copy c1 = FakeDB.getCopy("c1");
		Copy c2 = FakeDB.getCopy("c2");
		BookShelf shelf = new BookShelf();
		shelf.putCopy(c1);
		shelf.putCopy(c2);
		assertTrue(shelf.getCopies().size() == 2);
	}
	
	@Test
	public void grabCopiesTest() {
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		Patron p1 = FakeDB.getPatron("p1");
		assertTrue(!shelf.getCopies().isEmpty());
		Controller controller = new Controller();
		controller.assignCopyToPatron(1, shelf, p1);
		assertTrue(!p1.getCopiesCarry().isEmpty());
		assertTrue(!shelf.getCopies().contains(p1.getCopiesCarry().get(0)));
	}
	
	@Test
	public void checkOutTest() {
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		Patron p1 = FakeDB.getPatron("p1");
		Worker w1 = FakeDB.getWorker("w1");
		Controller controller = new Controller();
		
		Copy c1 = controller.assignCopyToPatron(1, shelf, p1);
		Copy c2 = controller.assignCopyToPatron(2, shelf, p1);
		
		
		
		
		for (int i = p1.getCopiesCarry().size() - 1; i >= 0; i--) {
			Copy c = p1.getCopiesCarry().get(i); 
			controller.checkOutCopy(w1, p1, c);
		}
		assertTrue(p1.getCopiesCarry().isEmpty());
		assertTrue(!p1.getCopiesOut().isEmpty());
		assertTrue(p1.getCopiesOut().contains(c1));
		assertTrue(p1.getCopiesOut().contains(c2));
		assertTrue(c1.getDueDate() != null);
		assertTrue(c2.getDueDate() != null);
		assertTrue(c1.getOutTo() == p1);
		assertTrue(c2.getOutTo() == p1);
		
	}
	
	@Test
	public void checkInTest() {
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		Patron p1 = FakeDB.getPatron("p1");
		Worker w1 = FakeDB.getWorker("w1");
		
		Controller controller = new Controller();
		
		Copy c1 = controller.assignCopyToPatron(1, shelf, p1);
		Copy c2 = controller.assignCopyToPatron(2, shelf, p1);
		
		for (int i = p1.getCopiesCarry().size() - 1; i >= 0; i--) {
			Copy c = p1.getCopiesCarry().get(i); 
			controller.checkOutCopy(w1, p1, c);
		}
		
		for (int a = p1.getCopiesOut().size() - 1; a >= 0; a--) {
			Copy c = p1.getCopiesOut().get(a); 
			controller.checkInCopy(w1, p1, c);
		}
		assertTrue(p1.getCopiesCarry().isEmpty());
		assertTrue(p1.getCopiesOut().isEmpty());
		assertTrue(c1.getOutTo() == null);
		assertTrue(c2.getOutTo() == null);
	}
	
	@Test
	public void addHoldTest() {
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		Patron p1 = FakeDB.getPatron("p2");
		Worker w1 = FakeDB.getWorker("w1");
		
		Controller controller = new Controller();
		Copy c1 = controller.assignCopyToPatron(1, shelf, p1);
		Hold h = controller.addHold(w1, p1, "damaged", c1.getCopyID());
		
		Service service = new Service();
		
		List<Hold> holds = service.getHolds(p1.getPatronID());
		assertTrue(!holds.isEmpty());
		assertTrue(holds.contains(h));
	}
	
	@Test
	public void removeHoldTest() {
		BookShelf shelf = new BookShelf();
		shelf.putCopies(FakeDB.getCopies());
		Patron p1 = FakeDB.getPatron("p2");
		Worker w1 = FakeDB.getWorker("w1");
		
		Controller controller = new Controller();
		Copy c1 = controller.assignCopyToPatron(1, shelf, p1);
		Hold h = controller.addHold(w1, p1, "damaged", c1.getCopyID());
		
		Service service = new Service();
		
		List<Hold> holds = service.getHolds(p1.getPatronID());
		
		controller.removeHold(w1, p1, h.getId());
		assertTrue(!holds.contains(h));
	}
	

}
