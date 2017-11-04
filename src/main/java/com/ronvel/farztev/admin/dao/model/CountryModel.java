package com.ronvel.farztev.admin.dao.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CountryModel extends BaseModel {
	private String name;
	private String abbreviation;
	private String description;
	private String trip;
	private String why;
	private String favorite;
	private String conclusion;
	private Date beginning;
	private Date ending;
	private Date created;
	private Date updated;
	private Boolean online;
	private String continent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrip() {
		return trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public Date getBeginning() {
		return beginning;
	}

	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	public Date getEnding() {
		return ending;
	}

	public void setEnding(Date ending) {
		this.ending = ending;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

}
