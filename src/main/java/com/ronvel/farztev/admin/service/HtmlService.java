package com.ronvel.farztev.admin.service;

import java.io.IOException;

import com.ronvel.farztev.admin.component.Homepage;

public interface HtmlService {

	String generateHomepage(Homepage homepage) throws IOException;

}
