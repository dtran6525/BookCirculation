package main.java.circulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public void checkOut(Patron patron, Copy copy) {
		eventLog.addEvent(new Date(), this + " is checking out " + patron);
		Service service = new Service();
		Date dueDate = service.getDueDate(); 
		copy.setDueDate(dueDate);
		eventLog.addEvent(new Date(), dueDate + " is assigned to " + copy);
		patron.checkCopyOut(copy);
	}
	
	
	public List<Copy> checkIn(Patron patron) {
		List<Copy> checkInCopies = new ArrayList<>();
		eventLog.addEvent(new Date(), this + " is checking in " + patron);
		if (verifyPatron(patron)) {
			for (int i = patron.getCopiesOut().size() - 1 ; i >= 0 ; i-- ) {
				Copy c = patron.getCopiesOut().get(i);
				if (scanCopy(c)) {
					patron.checkCopyIn(c);
					checkInCopies.add(c);
				}
			}
		} 
		return checkInCopies;
	}
	public void checkIn(Patron patron, Copy copy) {
		eventLog.addEvent(new Date(), this + " is checking in " + patron);
		patron.checkCopyIn(copy);
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

	public void identifyPatron(Patron activePatron, boolean result) {
		String s = result ? " has successfully identified " : " has failed to identify ";
		eventLog.addEvent(new Date(), this + s + activePatron);
	}

	public void addHold(Patron activePatron, String holdDesc, String copyId) {
		Service service = new Service();
		Hold h = service.addHold(activePatron, holdDesc, copyId);
		eventLog.addEvent(new Date(), this + " added " + h + " to " + activePatron);
		
	}

	public void removeHold(Patron activePatron, String holdId) {
		Service service = new Service();
		Hold h = service.removeHold(activePatron, holdId);
		eventLog.addEvent(new Date(), this + " removed " + h + " from " + activePatron);
	}



}
