package circulation;


import static org.junit.Assert.*;

import org.junit.Test;

import circulation.FakeDB;
import circulation.Patron;
import circulation.Copy;



public class PatronTest
{

	@Test
	public void test()
	{
		Patron p1 = FakeDB.getPatron("P1");
		Copy c1 = FakeDB.getCopy("C1");
		Copy c2 = FakeDB.getCopy("C2");
		
		assertTrue(p1.getCopiesOut().isEmpty());
		
		assertTrue(p1.checkCopyOut(c1));
		assertTrue(p1.getCopiesOut().size() == 1);
		assertTrue(p1.getCopiesOut().contains(c1));
		assertTrue(c1.getOutTo().equals(p1));
		
		assertTrue(p1.checkCopyOut(c2));
		assertTrue(p1.getCopiesOut().size() == 2);
		assertTrue(p1.getCopiesOut().contains(c2));
		assertTrue(c2.getOutTo().equals(p1));
		
		assertTrue(p1.checkCopyIn(c1));
		assertTrue(p1.getCopiesOut().size() == 1);
		assertTrue(!p1.getCopiesOut().contains(c1));
		assertTrue(c1.getOutTo() == null);
		
		assertTrue(p1.checkCopyIn(c2));
		assertTrue(p1.getCopiesOut().isEmpty());
		assertTrue(!p1.getCopiesOut().contains(c2));
		assertTrue(c2.getOutTo() == null);
	}

}
