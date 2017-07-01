package circulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventLog {
	public List<TRLEvent> events = new ArrayList<>();
	public void addEvent(Date date, String description) {
		this.events.add(new TRLEvent(date, description));
	}
}
