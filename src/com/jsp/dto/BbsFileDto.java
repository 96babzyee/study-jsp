package com.jsp.dto;

public class BbsFileDto {
	private String fileId;
	private String bbsId;
	private String orgn_file_nm;
	private String save_file_nm;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getOrgn_file_nm() {
		return orgn_file_nm;
	}

	public void setOrgn_file_nm(String orgn_file_nm) {
		this.orgn_file_nm = orgn_file_nm;
	}

	public String getSave_file_nm() {
		return save_file_nm;
	}

	public void setSave_file_nm(String save_file_nm) {
		this.save_file_nm = save_file_nm;
	}

	@Override
	public String toString() {
		return "BbsFileDto [fileId=" + fileId + ", bbsId=" + bbsId + ", orgn_file_nm=" + orgn_file_nm
				+ ", save_file_nm=" + save_file_nm + "]";
	}

}
