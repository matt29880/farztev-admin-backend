package com.ronvel.farztev.admin.service;

import java.io.IOException;
import java.util.List;

import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.TripPage;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListMedia;

public interface HtmlService {

	String generateHomepage(Homepage homepage) throws IOException;
	
	String generateTrip(TripPage tripPage) throws IOException;

	String generateArticle(Article article) throws IOException;

	String generateAlbum(Album album, List<ListMedia> medias) throws IOException;

}
