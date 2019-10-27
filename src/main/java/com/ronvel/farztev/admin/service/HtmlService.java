package com.ronvel.farztev.admin.service;

import java.io.IOException;

import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.TripPage;

public interface HtmlService {

	String generateHomepage(Homepage homepage) throws IOException;
	
	String generateTrip(TripPage tripPage) throws IOException;

}
