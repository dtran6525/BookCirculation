package circulation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Service {
	public boolean hasHolds(String patronID){
		return FakeDB.getHolds(patronID) != null && FakeDB.getHolds(patronID).size() >= 1;
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
	
	
	
}
