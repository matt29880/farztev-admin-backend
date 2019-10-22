package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.Timeline;
import com.ronvel.farztev.admin.service.impl.HtmlServiceImpl;
import com.ronvel.farztev.admin.service.impl.PublishServiceImpl;

public class HtmlServiceImplTest {

	private final HtmlServiceImpl sut = new HtmlServiceImpl();

	@Test
	public void generateHomepage() throws IOException {
		Homepage homepage = new Homepage();
		Timeline timeline1 = new Timeline();
		timeline1.setFuture(true);
		timeline1.setTitle("Valencia");
		timeline1.setSummary("We'll go to Valencia to visit this beautiful spanish city.");
		timeline1.setStart("12 MAY 2019");
		timeline1.setEnd("15 MAY 2019");
		
		Timeline timeline2 = new Timeline();
		timeline2.setFuture(true);
		timeline2.setTitle("Göteborg");
		timeline2.setSummary("Göteborg is located in the south-west of Sweden");
		timeline2.setStart("28 SEPTEMBER 2018");
		timeline2.setEnd("28 SEPTEMBER 2018");
		
		homepage.setTimelines(List.of(timeline1, timeline2));
		String actual = sut.generateHomepage(homepage);
		String expected = loadResource("html/homepage.html");
		FileUtils.write(new File("/home/mathieu/tmp/html/homepage.html"), actual);
		PublishServiceImpl.copyCss("/home/mathieu/tmp/html");
		assertEquals(expected, actual);
	}

	private String loadResource(String path) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(path).getFile());
		return FileUtils.readFileToString(file, "UTF-8");
	}
}
