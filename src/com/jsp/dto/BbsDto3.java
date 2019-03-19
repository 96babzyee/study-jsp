package com.jsp.dto;

public class BbsDto3 {
	private String bbsId;
	private String bbsCategory;
	private String id;
	private String bbsTitle;
	private String bbsContent;
	private String bbsDate;
	private String bbsHit;
	
	public String getBbsId() {
		return bbsId;
	}
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	public String getBbsCategory() {
		return bbsCategory;
	}
	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsHit() {
		return bbsHit;
	}
	public void setBbsHit(String bbsHit) {
		this.bbsHit = bbsHit;
	}
	@Override
	public String toString() {
		return "BbsDto3 [bbsId=" + bbsId + ", bbsCategory=" + bbsCategory + ", id=" + id + ", bbsTitle=" + bbsTitle
				+ ", bbsContent=" + bbsContent + ", bbsDate=" + bbsDate + ", bbsHit=" + bbsHit + "]";
	}
	
	
}
