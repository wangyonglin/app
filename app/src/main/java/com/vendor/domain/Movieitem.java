package com.vendor.domain;

import java.io.Serializable;

public class Movieitem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1042704683851298931L;

	    private Integer id;
	private String name;
	private Integer number;
	private String src;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
}
