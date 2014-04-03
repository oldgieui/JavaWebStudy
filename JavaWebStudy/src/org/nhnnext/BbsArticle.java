package org.nhnnext;

public class BbsArticle {
	private String name;
	private String title;
	private String contents;

	public BbsArticle(String name, String title, String contents) {
		this.name = name;
		this.title = title;
		this.contents = contents;
	}
	
	public BbsArticle() {
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
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
