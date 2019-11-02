package com.ronvel.farztev.admin.component;

import java.util.Date;
import java.util.List;

import com.ronvel.farztev.admin.controller.dto.ListMedia;

import lombok.Data;

@Data
public class AlbumPage {
	private Long id;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private String thumbnailUrl;
	private List<ListMedia> images;
}
