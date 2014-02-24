package es.udc.mashup.productnews.atom;

import java.util.Calendar;
import java.util.List;

public class FeedTO {
	
	private String title;
	private String author;
	private Calendar updated;
	private String link;
	private String description;
	private List<EntryTO> entries;
	
	public FeedTO() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EntryTO> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryTO> entries) {
		this.entries = entries;
	}
	
	

}
