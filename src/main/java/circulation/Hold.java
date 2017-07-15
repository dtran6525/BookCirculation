package  main.java.circulation;
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
	
	@Override
	public String toString() {
		return "Hold [id=" + id + ", description=" + description + ", date=" + date + "]";
	}

	public Patron getPatron() {
		return patron;
	}
	public String getPatronID() {
		return this.getPatron().getPatronID();
	}
	public String getId() {
		return id;
	}
	


}
