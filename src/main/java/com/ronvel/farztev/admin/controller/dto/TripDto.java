package com.ronvel.farztev.admin.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ronvel.farztev.admin.controller.config.LocalDateDeserializer;
import com.ronvel.farztev.admin.controller.config.LocalDateSerializer;

import lombok.Data;

@Data
public class TripDto {
	private Long id;
	private String name;
	private String summary;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate start;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate end;
	private String periodDescription;
	private Long thumbnailId;
	private String thumbnailUrl;
	private boolean online;
}
