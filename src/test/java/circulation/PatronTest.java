package test.java.circulation;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import main.java.circulation.BookShelf;
import main.java.circulation.Controller;
import main.java.circulation.Copy;
import main.java.circulation.EventLog;
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
	public void createPatron() {
		Patron p1 = new Patron(null, null, null);
		p1.setEventLog(new EventLog());
		p1.setHolds(new ArrayList<>());
		assertTrue(p1.getHolds().isEmpty());
		String name = "David";
		p1.setName(name);
		assertTrue(p1.getName() == name);
		assertTrue(p1.getEventLog().getEvents().isEmpty());
		String id = "10";
		p1.setPatronID(id);
		assertTrue(p1.getPatronID() == id);
		assertTrue(p1.hashCode() == p1.hashCode());
		assertTrue(p1.equals(p1));
		Patron p2 = new Patron(null, null, null);
		Patron p3 = new Patron(null, null, null);
		assertTrue(p2.equals(p3) && p2.hashCode() == p3.hashCode());
		assertTrue(!p2.equals(p1));
		p2.setPatronID("11");
		assertTrue(!p1.equals(p2));
		p2.setPatronID("10");
		assertTrue(p1.equals(p2));
		assertTrue(!p1.equals(null));
		assertTrue(!p1.equals(new Worker("10", "w1", new EventLog())));
	}
	
	@Test
	public void testLeave() {
		Patron p1 = new Patron("p1", "Peter", new EventLog());
		p1.leave();
		assertTrue(!p1.getEventLog().getEvents().isEmpty());
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
		
		List<Copy> copies = new ArrayList<>();
		Copy c1 = shelf.getCopies().get(0);
		Copy c2 = shelf.getCopies().get(1);
		copies.add(c1);
		copies.add(c2);
		p1.setCopiesCarry(copies);
		
		p1.setCopiesOut(copies);
		assertTrue(!p1.checkCopyOut(c1));
		p1.setCopiesOut(new ArrayList<Copy>());
		
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
		
		List<Copy> copies = new ArrayList<>();
		Copy c1 = shelf.getCopies().get(0);
		Copy c2 = shelf.getCopies().get(1);
		copies.add(c1);
		copies.add(c2);
		p1.setCopiesOut(copies);
		
		for (int a = p1.getCopiesOut().size() - 1; a >= 0; a--) {
			Copy c = p1.getCopiesOut().get(a); 
			controller.checkInCopy(w1, p1, c);
		}
		assertTrue(p1.getCopiesCarry().isEmpty());
		assertTrue(p1.getCopiesOut().isEmpty());
		assertTrue(c1.getOutTo() == null);
		assertTrue(c2.getOutTo() == null);
		
		
		Copy c3 = shelf.getCopies().get(2);
		p1.setCopiesOut(copies);
		assertTrue(!p1.checkCopyIn(c3));
		
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
