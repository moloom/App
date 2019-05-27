package com.app.pojo;

public class Data_flatForm {
	private Integer id;
	private Integer flatForm;
	private String flatFormName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFlatForm() {
		return flatForm;
	}
	public void setFlatForm(Integer flatForm) {
		this.flatForm = flatForm;
	}
	public String getFlatFormName() {
		return flatFormName;
	}
	public void setFlatFormName(String flatFormName) {
		this.flatFormName = flatFormName;
	}
	@Override
	public String toString() {
		return "Data_flatForm [id=" + id + ", flatForm=" + flatForm + ", flatFormName=" + flatFormName + "]";
	}
	
}
