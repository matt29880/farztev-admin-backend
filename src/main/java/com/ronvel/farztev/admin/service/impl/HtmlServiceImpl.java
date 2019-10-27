package com.ronvel.farztev.admin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.TripPage;
import com.ronvel.farztev.admin.service.HtmlService;

@Service
public class HtmlServiceImpl implements HtmlService {

	private final Handlebars handlebars;
	
	public HtmlServiceImpl() {
		handlebars = new Handlebars();
	}
	
	@Override
	public String generateHomepage(Homepage homepage) throws IOException {
		String top = loadTemplate("templates/top.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");
		
		String templateAsString = loadTemplate("templates/homepage.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return top + template.apply(homepage) + bottom;
	}

	@Override
	public String generateTrip(TripPage tripPage) throws IOException {
		String top = loadTemplate("templates/topN1.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");
		
		String templateAsString = loadTemplate("templates/trip.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return top + template.apply(tripPage) + bottom;
	}

	private String loadTemplate(String path) throws IOException {
		InputStream is = HtmlServiceImpl.class.getClassLoader().getResourceAsStream(path);
		return IOUtils.toString(is, StandardCharsets.UTF_8);
	}
	
	

}
