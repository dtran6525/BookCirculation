package circulation;

import java.util.List;

public class Worker {
	private String id;
	private String name;
	private EventLog eventLog;
	
	
	public Worker(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public void checkOut(Patron patron, List<Copy> copies) {
		Service service = new Service();
		for (Copy c : copies) {
			if (scanCopy(c)) {
				c.setDueDate(service.getDueDate());
				patron.checkCopyOut(c);
			}
		}
	}

	public boolean verifyPatron(Patron patron) {
		Service service = new Service();
		return service.lookupPatron(patron.getPatronID())!= null && 
			   service.hasHolds(patron.getPatronID());
	}
	public boolean scanCopy(Copy copy) {
		Service service = new Service();
		return service.lookupCopy(copy.getCopyID()) != null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public EventLog getEventLog() {
		return eventLog;
	}

	public void setEventLog(EventLog eventLog) {
		this.eventLog = eventLog;
	}



}
