package circulation;

import java.util.Date;

public class Worker {
	private String id;
	private String name;
	private EventLog eventLog;
	
	
	public Worker(String id, String name, EventLog log) {
		super();
		this.id = id;
		this.name = name;
		this.eventLog = log;
	}
	
	public void checkOut(Patron patron) {
		Service service = new Service();
		if (verifyPatron(patron)) {
			for (Copy c : patron.getCopiesCarry()) {
				if (scanCopy(c)) {
					c.setDueDate(service.getDueDate());
					patron.checkCopyOut(c);
				}
			}
		} 
	}

	public boolean verifyPatron(Patron patron) {
		Service service = new Service();
		if (service.lookupPatron(patron.getPatronID()) == null) {
			eventLog.addEvent(new Date(), "Patron does not exist");
			return false;
		} else if ( service.hasHolds(patron.getPatronID())) {
			eventLog.addEvent(new Date(), "Patron has holds");
			return false;
		}
		return true;
	}
	public boolean scanCopy(Copy copy) {
		Service service = new Service();
		if (service.lookupCopy(copy.getCopyID()) == null) {
			eventLog.addEvent(new Date(), "Copy does not exist in the system");
			return false;
		}
		return true;
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
