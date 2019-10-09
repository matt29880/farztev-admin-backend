package com.ronvel.farztev.admin.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ronvel.farztev.admin.controller.config.LocalDateTimeDeserializer;
import com.ronvel.farztev.admin.controller.config.LocalDateTimeSerializer;

import lombok.Data;

@Data
public class TripDto {
	private Long id;
	private String name;
	private String summary;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime start;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime end;
	private Long thumbnail;
	private boolean online;
}
