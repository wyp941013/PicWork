package com.work.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ImageClassify entity. @author MyEclipse Persistence Tools
 */

public class ImageClassify implements java.io.Serializable {

	// Fields

	private Integer imageClassifyId;
	private String imageClassifyName;
	private String imageClassifyDespretion;
	private Date createTime;
	private Integer editorUserId;
	private Set imageInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public ImageClassify() {
	}

	/** minimal constructor */
	public ImageClassify(String imageClassifyName, Date createTime,
			Integer editorUserId) {
		this.imageClassifyName = imageClassifyName;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
	}

	/** full constructor */
	public ImageClassify(String imageClassifyName,
			String imageClassifyDespretion, Date createTime,
			Integer editorUserId, Set imageInfos) {
		this.imageClassifyName = imageClassifyName;
		this.imageClassifyDespretion = imageClassifyDespretion;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
		this.imageInfos = imageInfos;
	}

	// Property accessors

	public Integer getImageClassifyId() {
		return this.imageClassifyId;
	}

	public void setImageClassifyId(Integer imageClassifyId) {
		this.imageClassifyId = imageClassifyId;
	}

	public String getImageClassifyName() {
		return this.imageClassifyName;
	}

	public void setImageClassifyName(String imageClassifyName) {
		this.imageClassifyName = imageClassifyName;
	}

	public String getImageClassifyDespretion() {
		return this.imageClassifyDespretion;
	}

	public void setImageClassifyDespretion(String imageClassifyDespretion) {
		this.imageClassifyDespretion = imageClassifyDespretion;
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

	public Set getImageInfos() {
		return this.imageInfos;
	}

	public void setImageInfos(Set imageInfos) {
		this.imageInfos = imageInfos;
	}

}