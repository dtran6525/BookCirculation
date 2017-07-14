package circulation;
import java.util.Date;

public class Copy
{
	private String copyID;
	private Textbook textbook;
	
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

	public Patron getOutTo() {
		return outTo;
	}
	public void setOutTo(Patron outTo) {
		this.outTo = outTo;
	}

	public Copy(String copyID, Textbook textbook)
	{
		this.copyID = copyID;
		this.textbook = textbook;
	}
	
	public Textbook getTextbook() {
		return textbook;
	}
	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}


	@Override
	public String toString() {
		return "Copy [copyID=" + copyID + ", textbook=" + textbook + "]";
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
	
	}
}
