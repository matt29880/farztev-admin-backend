package com.ronvel.farztev.admin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.service.HtmlService;

@Service
public class HtmlServiceImpl implements HtmlService {

	@Override
	public String generateHomepage(Homepage homepage) throws IOException {
		Handlebars handlebars = new Handlebars();
//		handlebars.setPrettyPrint(true);

		String templateAsString = loadTemplate("templates/homepage.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return template.apply(homepage);
	}

	private String loadTemplate(String path) throws IOException {
		InputStream is = HtmlServiceImpl.class.getClassLoader().getResourceAsStream(path);
		return IOUtils.toString(is, StandardCharsets.UTF_8);
	}

}
