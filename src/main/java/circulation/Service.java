package  main.java.circulation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Service {
	public boolean hasHolds(String patronID){
		return FakeDB.getHolds(patronID) != null && FakeDB.getHolds(patronID).size() >= 1;
	}
	
	public List<Hold> getHolds(String patronID){
		return FakeDB.getHolds(patronID) == null ? new ArrayList<>() : FakeDB.getHolds(patronID) ;
	}
	
	public Patron lookupPatron(String patronID) {
		return FakeDB.getPatron(patronID);
	}
	
	public Copy lookupCopy(String copyID) {
		return FakeDB.getCopy(copyID);
	}
	
	public Date getDueDate() {
		return Date.from(LocalDate.now().plusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public Hold addHold(Patron activePatron, String holdDesc, String copyId) {
		Copy c = lookupCopy(copyId);
		Hold h = new Hold(UUID.randomUUID().toString(), holdDesc, new Date(), activePatron, c);
		if (FakeDB.getHolds(activePatron.getPatronID()) == null) {
			List<Hold> holds = new ArrayList<>();
			holds.add(h);
			FakeDB.getHoldsLookup().put(activePatron.getPatronID(), holds);
		} else {
			FakeDB.getHolds(activePatron.getPatronID()).add(h);
		}
		return h;
	}
	
	
	
	
}
