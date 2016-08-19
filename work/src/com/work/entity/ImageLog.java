package com.work.entity;

import java.util.Date;

/**
 * ImageLog entity. @author MyEclipse Persistence Tools
 */

public class ImageLog implements java.io.Serializable {

	// Fields

	private Integer imageLogId;
	private Integer content;
	private String description;
	private Date createTime;
	private Integer editorUserId;

	// Constructors

	/** default constructor */
	public ImageLog() {
	}

	/** minimal constructor */
	public ImageLog(Integer content) {
		this.content = content;
	}

	/** full constructor */
	public ImageLog(Integer content, String description, Date createTime,
			Integer editorUserId) {
		this.content = content;
		this.description = description;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
	}

	// Property accessors

	public Integer getImageLogId() {
		return this.imageLogId;
	}

	public void setImageLogId(Integer imageLogId) {
		this.imageLogId = imageLogId;
	}

	public Integer getContent() {
		return this.content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}