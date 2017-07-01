package circulation;

public class TRLApp {
	public static void main(String argsp[]) {
		BookShelf shelf = new BookShelf();
		
		Copy c1 = FakeDB.getCopy("c1");
		Copy c2 = FakeDB.getCopy("c2");
		Copy c3 = FakeDB.getCopy("c3");
		Copy c4 = FakeDB.getCopy("c4");
		Copy c5 = FakeDB.getCopy("c5");
		Copy c6 = FakeDB.getCopy("c6");
		
		shelf.putCopy(c1);
		shelf.putCopy(c2);
		shelf.putCopy(c3);
		shelf.putCopy(c4);
		shelf.putCopy(c5);
		shelf.putCopy(c6);
		
		Patron p1 = FakeDB.getPatron("p1");
		Patron p2 = FakeDB.getPatron("p2");
		Patron p3 = FakeDB.getPatron("p3");
		Worker w1 = FakeDB.getWorker("w1");
		Worker w2 = FakeDB.getWorker("w2");
	}
}
