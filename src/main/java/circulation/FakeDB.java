package  main.java.circulation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeDB
{
	private static Map<String,Patron> patronStore = new HashMap<>();
	private static Map<String, Worker> workerStore = new HashMap<>();
	public static Map<String, Patron> getPatronStore() {
		return patronStore;
	}

	private static Map<String,Copy> copyStore = new HashMap<>();
	private static Map<String, List<Hold>> holdsLookup = new HashMap<>();
	
	static // the following runs once when class is loaded: "static initializer"
	{
		EventLog log = new EventLog();
		Patron p1 = new Patron("p1", "John", log);
		Patron p2 = new Patron("p2", "James", log);
		Patron p3 = new Patron("p3", "Jill", log);
		
		patronStore.put("p1", p1);
		patronStore.put("p2", p2);
		patronStore.put("p3", p3);
		
		workerStore.put("w1", new Worker("w1", "Peter", log));
		workerStore.put("w2", new Worker("w2", "James", log));
		
		Textbook b1 = new Textbook("b1", "Programming in Java");
		Textbook b2 = new Textbook("b2", "Programming in Python");
		Textbook b3 = new Textbook("b3", "Programming in Scala");
		
		Copy c1 = new Copy("c1", b1);
		Copy c2 = new Copy("c2", b2);
		Copy c3 = new Copy("c3", b3);
		Copy c4 = new Copy("c4", b1);
		Copy c5 = new Copy("c5", b2);
		Copy c6 = new Copy("c6", b3);
		
		copyStore.put("c1", c1);
		copyStore.put("c2", c2);
		copyStore.put("c3", c3);
		copyStore.put("c4", c4);
		copyStore.put("c5", c5);
		copyStore.put("c6", c6);
		
		List<Hold> holds1 = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			holds1.add(new Hold("h1", "30 days late", sdf.parse("2016/12/25"), p2, c3));
			holds1.add(new Hold("h2", "45 days late", sdf.parse("2016/12/25"), p2, c2));
			holds1.add(new Hold("h1", "spilled coffee on cover", sdf.parse("2016/12/25"), p3, c1));
			holds1.add(new Hold("h2", "torned cover", sdf.parse("2016/12/25"), p3, c2));
			holds1.add(new Hold("h1", "water damage", sdf.parse("2016/12/25"), p3, c3));
		
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		holdsLookup.putAll( holds1.stream().collect(Collectors.groupingBy(Hold::getPatronID)));
	}

	public static Patron getPatron(String patronID)
	{
		return patronStore.get(patronID);
	}
	
	public static List<Patron> getPatrons() {
		return new ArrayList<>(patronStore.values());
	}
	
	public static List<Worker> getWorkers() {
		return new ArrayList<>(workerStore.values());
	}
	
	public static List<Copy> getCopies() {
		return new ArrayList<>(copyStore.values());
	}
	
	public static Worker getWorker(String workerID) {
		return workerStore.get(workerID);
	}
	
	public static Copy getCopy(String copyID)
	{
		return copyStore.get(copyID);
	}
	
	public static List<Hold> getHolds(String patronId){
		return holdsLookup.get(patronId);
	}
	public static void setPatronStore(Map<String, Patron> patronStore) {
		FakeDB.patronStore = patronStore;
	}

	public static Map<String, Worker> getWorkerStore() {
		return workerStore;
	}

	public static void setWorkerStore(Map<String, Worker> workerStore) {
		FakeDB.workerStore = workerStore;
	}

	public static Map<String, Copy> getCopyStore() {
		return copyStore;
	}

	public static void setCopyStore(Map<String, Copy> copyStore) {
		FakeDB.copyStore = copyStore;
	}

	public static Map<String, List<Hold>> getHoldsLookup() {
		return holdsLookup;
	}

	public static void setHoldsLookup(Map<String, List<Hold>> holdsLookup) {
		FakeDB.holdsLookup = holdsLookup;
	}
}
