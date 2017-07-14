package circulation;

import java.util.Date;

public class TRLEvent {
	private Date date;
	private String description;
	
	public TRLEvent(Date date, String description) {
		super();
		this.date = date;
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TRLEvent [date=" + date + ", description=" + description + "]";
	}
	
	
}
