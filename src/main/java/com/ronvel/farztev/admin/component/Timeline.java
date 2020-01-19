package com.ronvel.farztev.admin.component;

import lombok.Data;

@Data
public class Timeline {
	private Long id;
	private String image;
	private String title;
	private String summary;
	private boolean future;
	private String start;
	private String end;
	private String periodDescription;
}
