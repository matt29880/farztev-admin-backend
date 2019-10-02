package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.ronvel.farztev.admin.component.Homepage;

public class HtmlServiceImpl {

	public String generateHomepage(Homepage homepage) throws IOException {
		Handlebars handlebars = new Handlebars();
//		handlebars.setPrettyPrint(true);

		String templateAsString = loadTemplate("templates/homepage.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return template.apply(homepage);
	}

	private String loadTemplate(String path) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		return FileUtils.readFileToString(file, "UTF-8");
	}

}
