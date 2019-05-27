package com.app.pojo;

public class Data_appStatus {
	private Integer id;
	private Integer statusCode;
	private String statusName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Override
	public String toString() {
		return "Data_status [id=" + id + ", statusCode=" + statusCode + ", statusName=" + statusName + "]";
	}
	
}
