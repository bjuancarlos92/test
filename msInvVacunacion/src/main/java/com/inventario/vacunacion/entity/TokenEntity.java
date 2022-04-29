package com.inventario.vacunacion.entity;

import java.util.Date;

public class TokenEntity {

	private String accessToken;
	private int expiryTime;
	private Date startDate;
	private Date endDate;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(int expiresIn) {
		this.expiryTime = expiresIn;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
