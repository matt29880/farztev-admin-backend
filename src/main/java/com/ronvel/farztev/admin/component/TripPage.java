package com.ronvel.farztev.admin.component;

import java.util.List;

import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.TripDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripPage {
	private TripDto trip;
	private List<Article> articles;
	private List<Album> albums;
}
