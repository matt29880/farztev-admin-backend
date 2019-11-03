package com.ronvel.farztev.admin.service;

import java.io.IOException;

import com.ronvel.farztev.admin.controller.dto.PublishType;

public interface PublishService {

	void publishAllWebsite(PublishType publishType) throws IOException;
	
}
