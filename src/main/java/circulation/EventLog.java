package  main.java.circulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventLog {
	private List<TRLEvent> events = new ArrayList<>();
	public void addEvent(Date date, String description) {
		TRLEvent e = new TRLEvent(date, description);
		System.out.println(e);
		this.events.add(e);
	}
	public List<TRLEvent> getEvents() {
		return events;
	}
}
