package com.ronvel.farztev.admin.component;

public class Timeline {
	private String image;
	private String title;
	private String summary;
	private boolean future;
	private String start;
	private String end;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public boolean isFuture() {
		return future;
	}

	public void setFuture(boolean future) {
		this.future = future;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
