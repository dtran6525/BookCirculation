package circulation;
import java.util.Date;

public class Hold {
	private String id;
	private String description;
	private Date date;
	private Patron patron;
	private Copy copy;
	
	public Hold(String id, String description, Date date, Patron patron, Copy copy) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.patron = patron;
		this.copy = copy;
	}
	
	
	public Patron getPatron() {
		return patron;
	}
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
	public String getPatronID() {
		return this.getPatron().getPatronID();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}


}
