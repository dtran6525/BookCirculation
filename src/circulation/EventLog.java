package circulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventLog {
	public List<TRLEvent> events = new ArrayList<>();
	public void addEvent(Date date, String description) {
		TRLEvent e = new TRLEvent(date, description);
		System.out.println(e);
		this.events.add(e);
	}
}
