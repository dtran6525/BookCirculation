package  main.java.circulation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patron
{
	private String name;
	private String patronID;
	private List<Copy> copiesOut = new ArrayList<>();
	private List<Copy> copiesCarry = new ArrayList<>();
	private EventLog eventLog;

	private List<Hold> holds = new ArrayList<>();

	public List<Hold> getHolds() {
		return holds;
	}

	public void setHolds(List<Hold> holds) {
		this.holds = holds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronID() {
		return patronID;
	}

	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}

	public EventLog getEventLog() {
		return eventLog;
	}

	public void setEventLog(EventLog eventLog) {
		this.eventLog = eventLog;
	}

	public List<Copy> getCopiesOut() {
		return copiesOut;
	}

	public void setCopiesOut(List<Copy> copiesOut) {
		this.copiesOut = copiesOut;
	}
	
	public List<Copy> getCopiesCarry() {
		return copiesCarry;
	}

	public void setCopiesCarry(List<Copy> copiesCarry) {
		this.copiesCarry = copiesCarry;
	}

	public Patron(String id, String name, EventLog log)
	{
		this.patronID = id;
		this.name = name;
		this.eventLog = log;
	}

	public void grabCopy(Copy c) {
		eventLog.addEvent(new Date(), "Patron grabs " + c + " from shelf");
		copiesCarry.add(c);
	}
	
	public boolean checkCopyOut(Copy c)
	{
		if (copiesOut.contains(c)) return false;
		copiesOut.add(c);
		copiesCarry.remove(c);
		c.setOutTo(this);
		return true;
	}
	public void leave() {
		eventLog.addEvent(new Date(), this + " has left the library");
	}

	public boolean checkCopyIn(Copy c)
	{
		if (!copiesOut.contains(c)) return false;
		copiesOut.remove(c);
		c.setOutTo(null);
		eventLog.addEvent(new Date(), "Patron is returning copy");
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patronID == null) ? 0 : patronID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patron other = (Patron) obj;
		if (patronID == null) {
			if (other.patronID != null)
				return false;
		} else if (!patronID.equals(other.patronID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patron [name=" + name + ", patronID=" + patronID + "]";
	}

}
