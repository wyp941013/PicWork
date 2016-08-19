package com.work.entity;

/**
 * ImageClassView entity. @author MyEclipse Persistence Tools
 */

public class ImageClassView implements java.io.Serializable {

//	// Fields
//
//	private ImageClassViewId id;
//
//	// Constructors
//
//	/** default constructor */
//	public ImageClassView() {
//	}
//
//	/** full constructor */
//	public ImageClassView(ImageClassViewId id) {
//		this.id = id;
//	}
//
//	// Property accessors
//
//	public ImageClassViewId getId() {
//		return this.id;
//	}
//
//	public void setId(ImageClassViewId id) {
//		this.id = id;
//	}
	private Integer imageClassifyId;
	private String imageClassifyName;
	private long num;

	// Constructors

	/** default constructor */

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

	public long getNum() {
		return this.num;
	}

	public void setNum(long num) {
		this.num = num;
	}


}