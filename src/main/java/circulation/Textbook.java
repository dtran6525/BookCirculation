package main.java.circulation;

public class Textbook {
	private String id;
	private String title;
	
	public Textbook(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Textbook [id=" + id + ", title=" + title + "]";
	}
	
}
