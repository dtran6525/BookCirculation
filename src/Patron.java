import java.util.ArrayList;
import java.util.List;

public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut = new ArrayList<>();

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

	public ArrayList<Copy> getCopiesOut() {
		return copiesOut;
	}

	public void setCopiesOut(ArrayList<Copy> copiesOut) {
		this.copiesOut = copiesOut;
	}

	public Patron(String id, String name)
	{
		this.patronID = id;
		this.name = name;
	}

	public boolean checkCopyOut(Copy c)
	{
		if (copiesOut.contains(c)) return false;
		copiesOut.add(c);
		c.setOutTo(this);
		return true;
	}

	public boolean checkCopyIn(Copy c)
	{
		if (!copiesOut.contains(c)) return true;
		copiesOut.remove(c);
		c.setOutTo(null);
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
		return "Patron [name=" + name + ", patronID=" + patronID + ", copiesOut=" + copiesOut + "]";
	}

	public static void main(String[] args)
	{
		Patron p1 = FakeDB.getPatron("P1");
		Copy c1 = FakeDB.getCopy("C1");
		Copy c2 = FakeDB.getCopy("C2");
		System.out.println("Before checking out first copy");
		System.out.println(p1);
		System.out.println();
		
		
		System.out.println("After checking out first copy");
		p1.checkCopyOut(c1);
		System.out.println(p1);
		System.out.println();
		
		System.out.println("After checking out second copy");
		p1.checkCopyOut(c2);
		System.out.println(p1);
		System.out.println();
		
		System.out.println("After checking in first copy");
		p1.checkCopyIn(c1);
		System.out.println(p1);
		System.out.println();
		
		System.out.println("After checking in second copy");
		p1.checkCopyIn(c2);
		System.out.println(p1);
		System.out.println();
		
	}

}
