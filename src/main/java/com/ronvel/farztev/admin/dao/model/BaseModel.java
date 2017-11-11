package com.ronvel.farztev.admin.dao.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "IdOrGenerated")
	@GenericGenerator(name = "IdOrGenerated", strategy = "com.ronvel.farztev.admin.dao.UseIdOrGenerate")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
