package org.nhnnext;

public class Bbs {
	private String name;
	private String title;
	private String contents;

	public Bbs(String name, String title, String contents) {
		this.name = name;
		this.title = title;
		this.contents = contents;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContents() {
		return contents;
	}
}
