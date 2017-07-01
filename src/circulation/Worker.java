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
		eventLog.addEvent(new Date(), this + " is checking out " + patron);
		Service service = new Service();
		if (verifyPatron(patron)) {
			for (int i = patron.getCopiesCarry().size() - 1 ; i >= 0 ; i-- ) {
				Copy c = patron.getCopiesCarry().get(i);
				if (scanCopy(c)) {
					Date dueDate = service.getDueDate(); 
					c.setDueDate(dueDate);
					eventLog.addEvent(new Date(), dueDate + " is assigned to " + c);
					patron.checkCopyOut(c);
				}
			}
		} 
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", name=" + name + "]";
	}

	public boolean verifyPatron(Patron patron) {
		Service service = new Service();
		if (service.lookupPatron(patron.getPatronID()) == null) {
			eventLog.addEvent(new Date(), patron + " does not exist");
			return false;
		} else if ( service.hasHolds(patron.getPatronID())) {
			eventLog.addEvent(new Date(), patron + " has " + service.getHolds(patron.getPatronID()));
			return false;
		}
		eventLog.addEvent(new Date(), this + " successfully verified " + patron);
		return true;
	}
	
	public boolean scanCopy(Copy copy) {
		Service service = new Service();
		if (service.lookupCopy(copy.getCopyID()) == null) {
			eventLog.addEvent(new Date(), copy + " does not exist in the system");
			return false;
		}
		eventLog.addEvent(new Date(), this + " scans " + copy);
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
