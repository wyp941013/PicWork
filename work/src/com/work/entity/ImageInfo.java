package com.work.entity;

import java.util.Date;

/**
 * ImageInfo entity. @author MyEclipse Persistence Tools
 */

public class ImageInfo implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private ImageClassify imageClassify;
	private String imageDespretion;
	private String imageAddress;
	private Integer imageRank;
	private String imageFormat;
	private Date createTime;
	private Integer editorUserId;

	// Constructors

	/** default constructor */
	public ImageInfo() {
	}

	/** minimal constructor */
	public ImageInfo(ImageClassify imageClassify, String imageAddress,
			Integer imageRank, String imageFormat, Date createTime,
			Integer editorUserId) {
		this.imageClassify = imageClassify;
		this.imageAddress = imageAddress;
		this.imageRank = imageRank;
		this.imageFormat = imageFormat;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
	}

	/** full constructor */
	public ImageInfo(ImageClassify imageClassify, String imageDespretion,
			String imageAddress, Integer imageRank, String imageFormat,
			Date createTime, Integer editorUserId) {
		this.imageClassify = imageClassify;
		this.imageDespretion = imageDespretion;
		this.imageAddress = imageAddress;
		this.imageRank = imageRank;
		this.imageFormat = imageFormat;
		this.createTime = createTime;
		this.editorUserId = editorUserId;
	}

	// Property accessors

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public ImageClassify getImageClassify() {
		return this.imageClassify;
	}

	public void setImageClassify(ImageClassify imageClassify) {
		this.imageClassify = imageClassify;
	}

	public String getImageDespretion() {
		return this.imageDespretion;
	}

	public void setImageDespretion(String imageDespretion) {
		this.imageDespretion = imageDespretion;
	}

	public String getImageAddress() {
		return this.imageAddress;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public Integer getImageRank() {
		return this.imageRank;
	}

	public void setImageRank(Integer imageRank) {
		this.imageRank = imageRank;
	}

	public String getImageFormat() {
		return this.imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
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