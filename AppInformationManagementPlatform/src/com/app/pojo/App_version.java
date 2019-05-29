package com.app.pojo;

import java.util.Date;

public class App_version {
	private Integer id;
	private Integer appId;
	private String versionNo;
	private String versionInfo;
	private Integer publishStatus;
	private Integer versionSize;
	private String APKLocPath;
	private String APKFileName;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;

	// app名称publishStatusName
	private String appName;

	private String publishStatusName; //项目发布状态名

	public String getPublishStatusName() {
		return publishStatusName;
	}

	public void setPublishStatusName(String publishStatusName) {
		this.publishStatusName = publishStatusName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getVersionSize() {
		return versionSize;
	}

	public void setVersionSize(Integer versionSize) {
		this.versionSize = versionSize;
	}

	public String getAPKLocPath() {
		return APKLocPath;
	}

	public void setAPKLocPath(String aPKLocPath) {
		APKLocPath = aPKLocPath;
	}

	public String getAPKFileName() {
		return APKFileName;
	}

	public void setAPKFileName(String aPKFileName) {
		APKFileName = aPKFileName;
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
		return "App_version [id=" + id + ", appId=" + appId + ", versionNo=" + versionNo + ", versionInfo="
				+ versionInfo + ", publishStatus=" + publishStatus + ", versionSize=" + versionSize + ", APKLocPath="
				+ APKLocPath + ", APKFileName=" + APKFileName + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", appName=" + appName
				+ ", publishStatusName=" + publishStatusName + "]";
	}

}
