package com.ronvel.farztev.admin.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "trip")
@Data
@EqualsAndHashCode(callSuper = false)
public class TripModel extends BaseModel {
	@NotNull
	@Size(max = 60)
	private String name;

	@Size(max = 2000)
	@Column(name = "summary", nullable = true, length = 65535, columnDefinition = "TEXT")
	private String summary;

	private LocalDateTime start;

	private LocalDateTime end;

	private Long thumbnail;

	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean online;

}
