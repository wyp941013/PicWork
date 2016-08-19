package com.work.entity;

import java.util.Date;

/**
 * ImageChange entity. @author MyEclipse Persistence Tools
 */

public class ImageChange implements java.io.Serializable {

	// Fields

	private Integer CId;
	private Integer imageId;
	private String changebName;
	private String changeContent;
	private Integer changebClassify;
	private Integer changeClassify;
	private String description;
	private Integer permission;
	private Date createTime;
	private String editorUserName;
	private Integer editorUserId;

	// Constructors

	/** default constructor */
	public ImageChange() {
	}

	/** minimal constructor */
	public ImageChange(Integer permission, Date createTime,
			String editorUserName, Integer editorUserId) {
		this.permission = permission;
		this.createTime = createTime;
		this.editorUserName = editorUserName;
		this.editorUserId = editorUserId;
	}

	/** full constructor */
	public ImageChange(Integer imageId, String changebName,
			String changeContent, Integer changebClassify,
			Integer changeClassify, String description, Integer permission,
			Date createTime, String editorUserName, Integer editorUserId) {
		this.imageId = imageId;
		this.changebName = changebName;
		this.changeContent = changeContent;
		this.changebClassify = changebClassify;
		this.changeClassify = changeClassify;
		this.description = description;
		this.permission = permission;
		this.createTime = createTime;
		this.editorUserName = editorUserName;
		this.editorUserId = editorUserId;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getChangebName() {
		return this.changebName;
	}

	public void setChangebName(String changebName) {
		this.changebName = changebName;
	}

	public String getChangeContent() {
		return this.changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public Integer getChangebClassify() {
		return this.changebClassify;
	}

	public void setChangebClassify(Integer changebClassify) {
		this.changebClassify = changebClassify;
	}

	public Integer getChangeClassify() {
		return this.changeClassify;
	}

	public void setChangeClassify(Integer changeClassify) {
		this.changeClassify = changeClassify;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditorUserName() {
		return this.editorUserName;
	}

	public void setEditorUserName(String editorUserName) {
		this.editorUserName = editorUserName;
	}

	public Integer getEditorUserId() {
		return this.editorUserId;
	}

	public void setEditorUserId(Integer editorUserId) {
		this.editorUserId = editorUserId;
	}

}