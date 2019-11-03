package com.ronvel.farztev.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ronvel.farztev.admin.controller.dto.PublishType;
import com.ronvel.farztev.admin.service.PublishService;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class PublishController {

	@Autowired
	private PublishService publishService;

	@RequestMapping(value = "/api/publish/{publishType}", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody String index(@PathVariable("publishType") PublishType publishType)
			throws IOException {
		publishService.publishAllWebsite(publishType);
		return "{\"response\":\"ok\"}";
	}

}
