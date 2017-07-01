package circulation;

import java.util.HashMap;
import java.util.Map;

public class BookShelf {
	private Map<String, Copy> availableCopies = new HashMap<>();
	public void putCopy(Copy c) {
		this.availableCopies.put(c.getCopyID(), c);
	}
	public Copy takeCopy(String copyID) {
		return this.availableCopies.remove(copyID);
	}
}
