package com.work.entity;

import java.util.Date;

/**
 * ImageQueryInfo entity. @author MyEclipse Persistence Tools
 */

public class ImageQueryInfo implements java.io.Serializable {

	// Fields

	private Integer queryId;
	private String imageUploadingAddress;
	private Date createTime;
	private Double queryTime;
	private Integer editorUserId;
	private Integer state;

	// Constructors

	/** default constructor */
	public ImageQueryInfo() {
	}

	public Double getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Double queryTime) {
		this.queryTime = queryTime;
	}

	/** full constructor */
	public ImageQueryInfo(String imageUploadingAddress, Date createTime,
			Integer editorUserId, Integer state) {
		this.imageUploadingAddress = imageUploadingAddress;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
		this.state = state;
	}

	// Property accessors

	public Integer getQueryId() {
		return this.queryId;
	}


	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public String getImageUploadingAddress() {
		return this.imageUploadingAddress;
	}

	public void setImageUploadingAddress(String imageUploadingAddress) {
		this.imageUploadingAddress = imageUploadingAddress;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEditorUserId() {
		return this.editorUserId;
	}

	public void setEditorUserId(Integer editorUserId) {
		this.editorUserId = editorUserId;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}