package com.ronvel.farztev.admin.component;

import lombok.Data;

@Data
public class Timeline {
	private Long id;
	private String image;
	private String title;
	private String summary;
	private boolean future;
	private int day;
	private String month;
	private int year;
	private String periodDescription;
	private String side; // left or right
}
