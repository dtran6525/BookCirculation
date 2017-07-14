package main.java.circulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookShelf {
	private Map<String, Copy> availableCopies = new HashMap<>();
	public void putCopy(Copy c) {
		this.availableCopies.put(c.getCopyID(), c);
	}
	public Copy passCopy(String copyID) {
		return this.availableCopies.remove(copyID);
	}
	
	public void putCopies(List<Copy> copies){
		for (Copy c : copies) {
			this.availableCopies.put(c.getCopyID(), c);
		}
	}
	public List<Copy> getCopies() {
		return new ArrayList<>(availableCopies.values());
	}
	public Map<String, Copy> getAvailableCopies() {
		return availableCopies;
	}
	
}
