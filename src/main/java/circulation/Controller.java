package  main.java.circulation;

public class Controller {
	public Patron pickPatron(String input){
		return FakeDB.getPatrons().get(Integer.parseInt(input) - 1);
	}
	
}
