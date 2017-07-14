package test.java.circulation;


import static org.junit.Assert.*;

import org.junit.Test;

import main.java.circulation.BookShelf;
import main.java.circulation.Copy;
import main.java.circulation.FakeDB;
import main.java.circulation.Patron;
import main.java.circulation.Worker;



public class PatronTest
{

	@Test
	public void test()
	{
		BookShelf shelf = new BookShelf();
		Patron p1 = FakeDB.getPatron("p1");
		Worker w1 = FakeDB.getWorker("w1");
		Copy c1 = FakeDB.getCopy("c1");
		Copy c2 = FakeDB.getCopy("c2");
		shelf.putCopy(c1);
		shelf.putCopy(c2);
		
		assertTrue(p1.getCopiesOut().isEmpty());
		assertTrue(p1.getCopiesCarry().isEmpty());
		assertTrue(p1.getEventLog().getEvents().isEmpty());
		
		p1.grabCopy(shelf.passCopy("c1"));
		p1.grabCopy(shelf.passCopy("c2"));
		assertTrue(!p1.getEventLog().getEvents().isEmpty());
		
		assertTrue(p1.getCopiesOut().isEmpty());
		assertTrue(p1.getCopiesCarry().size() == 2);
		
		w1.checkOut(p1);
		
		assertTrue(c1.getDueDate() != null);
		assertTrue(c1.getOutTo().equals(p1));
		assertTrue(!p1.getCopiesOut().isEmpty());
		assertTrue(p1.getCopiesOut().contains(c1));
		assertTrue(p1.getCopiesOut().contains(c2));
		
		
	}

}
