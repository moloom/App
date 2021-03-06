package com.app.pojo;

import java.util.Date;

public class Backend_user {
	private Integer id;
	private String userCode;
	private String userName;
	private Integer userTypeName;
	private String userPassword;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public Integer getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(Integer userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Backend_user [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userType=" + userTypeName
				+ ", userPassword=" + userPassword + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}
	

}
