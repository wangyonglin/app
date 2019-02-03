package com.vendor.domain;


import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Movie{
	private static final long serialVersionUID = -5223352039375659148L;

	private String uuid;
	
	private String name;
	private String category;
	private String staring;//主演
	private String image;

	private Set<Movieitem> movieitem = new HashSet<Movieitem>();
	private String description;
	private String releaseDate;
	private Boolean state;
	private String createDate;
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStaring() {
		return staring;
	}
	public void setStaring(String staring) {
		this.staring = staring;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Movieitem> getMovieitem() {
		return movieitem;
	}
	public void setMovieitem(Set<Movieitem> movieitem) {
		this.movieitem = movieitem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
