package es.udc.mashup.productnews.atom;

import java.util.Calendar;

public class EntryTO {
	
	private String id;
	private String title;
	private String summary;
	private Calendar updated;
	
	public EntryTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
	
	

}
