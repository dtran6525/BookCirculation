import java.util.Date;

public class Copy
{
	private String copyID;
	private String title;
	private Patron outTo;
	private Date dueDate;
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getCopyID() {
		return copyID;
	}
	public void setCopyID(String copyID) {
		this.copyID = copyID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Patron getOutTo() {
		return outTo;
	}
	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}

	public Copy(String copyID, String title)
	{
		this.copyID = copyID;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Copy [copyID=" + copyID + ", title=" + title + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copyID == null) ? 0 : copyID.hashCode());
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
		Copy other = (Copy) obj;
		if (copyID == null) {
			if (other.copyID != null)
				return false;
		} else if (!copyID.equals(other.copyID))
			return false;
		return true;
	}
	
	public static void main(String[] args)
	{
		Copy c1 = new Copy("C1", "Fun with Objects");
		Patron p1 = new Patron("Eric", "P47");

		System.out.println(c1);
		System.out.println(p1);
		
		Copy c2 = FakeDB.getCopy("C2");
	}
}
