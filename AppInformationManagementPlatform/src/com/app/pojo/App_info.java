package com.app.pojo;

import java.util.Date;

public class App_info {
	private Integer id;
	private String softwareName;
	private String APKName;
	private String supportRom;
	private String interfaceLanguage;
	private Integer softwareSize;
	private Date updateDate;
	private Integer devId;
	private String appInfo;
	private Integer status;
	private Date onSaleDate;
	private Date offSaleDate;
	private Integer flatfromId;
	private Integer categoryLevel3;
	private Integer downloads;
	private Integer createdBy;
	private Date creationDate;
	private Integer modifyBy;
	private Date modifyDate;
	private Integer categoryLevel1;
	private Integer categoryLevel2;
	private String logoPicPath;
	private String logoLocPath;
	private Integer versionId;

	// 外加字段
	private String flatformName;// 所属平台名称
	// App所属分类，一级分类，二级分类，三级分类 名称
	private String categoryLevel1Name;
	private String categoryLevel2Name;
	private String categoryLevel3Name;

	private String statusName;// app状态名称

	private String versionNo;// 最新版本号

	public String getFlatformName() {
		return flatformName;
	}

	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}

	public String getCategoryLevel2Name() {
		return categoryLevel2Name;
	}

	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.categoryLevel2Name = categoryLevel2Name;
	}

	public String getCategoryLevel3Name() {
		return categoryLevel3Name;
	}

	public void setCategoryLevel3Name(String categoryLevel3Name) {
		this.categoryLevel3Name = categoryLevel3Name;
	}

	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}

	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getAPKName() {
		return APKName;
	}

	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}

	public String getSupportRom() {
		return supportRom;
	}

	public void setSupportRom(String supportRom) {
		this.supportRom = supportRom;
	}

	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}

	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}

	public Integer getSoftwareSize() {
		return softwareSize;
	}

	public void setSoftwareSize(Integer softwareSize) {
		this.softwareSize = softwareSize;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOnSaleDate() {
		return onSaleDate;
	}

	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}

	public Date getOffSaleDate() {
		return offSaleDate;
	}

	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	public Integer getFlatfromId() {
		return flatfromId;
	}

	public void setFlatfromId(Integer flatfromId) {
		this.flatfromId = flatfromId;
	}

	public Integer getCategoryLevel3() {
		return categoryLevel3;
	}

	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
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

	public Integer getCategoryLevel1() {
		return categoryLevel1;
	}

	public void setCategoryLevel1(Integer categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}

	public Integer getCategoryLevel2() {
		return categoryLevel2;
	}

	public void setCategoryLevel2(Integer categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}

	public String getLogoPicPath() {
		return logoPicPath;
	}

	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	public String getLogoLocPath() {
		return logoLocPath;
	}

	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	@Override
	public String toString() {
		return "App_info [id=" + id + ", softwareName=" + softwareName + ", APKName=" + APKName + ", supportRom="
				+ supportRom + ", interfaceLanguage=" + interfaceLanguage + ", softwareSize=" + softwareSize
				+ ", updateDate=" + updateDate + ", devId=" + devId + ", appInfo=" + appInfo + ", status=" + status
				+ ", onSaleDate=" + onSaleDate + ", offSaleDate=" + offSaleDate + ", flatfromId=" + flatfromId
				+ ", categoryLevel3=" + categoryLevel3 + ", downloads=" + downloads + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", categoryLevel1=" + categoryLevel1 + ", categoryLevel2=" + categoryLevel2 + ", logoPicPath="
				+ logoPicPath + ", logoLocPath=" + logoLocPath + ", versionId=" + versionId + ", flatformName="
				+ flatformName + ", categoryLevel1Name=" + categoryLevel1Name + ", categoryLevel2Name="
				+ categoryLevel2Name + ", categoryLevel3Name=" + categoryLevel3Name + ", statusName=" + statusName
				+ ", versionNo=" + versionNo + "]";
	}

}
