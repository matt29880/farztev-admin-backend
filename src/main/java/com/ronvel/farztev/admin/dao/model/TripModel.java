package com.ronvel.farztev.admin.dao.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @Column(name = "start", columnDefinition = "DATE")
	private LocalDate start;

    @Column(name = "end", columnDefinition = "DATE")
	private LocalDate end;

	@Column(name = "thumbnail_id")
	private Long thumbnailId;

	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean online;

}
