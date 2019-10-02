package com.ronvel.farztev.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ronvel.farztev.admin.service.PublishService;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class PublishController {
	
	@Autowired
	private PublishService publishService;
	
	@RequestMapping(value = "/publish")
	public String index() {
		System.out.println("swagger-ui.html");
		return "redirect:swagger-ui.html";
	}
}
