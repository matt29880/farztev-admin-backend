package com.ronvel.farztev.admin.component;

import java.util.List;

public class Homepage {
	private String title = "Farztev.com";
	private List<Timeline> timelines;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Timeline> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}

}
