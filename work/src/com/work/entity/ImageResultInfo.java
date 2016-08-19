package com.work.entity;

/**
 * ImageResultInfo entity. @author MyEclipse Persistence Tools
 */

public class ImageResultInfo implements java.io.Serializable {

	// Fields

	private Integer resultId;
	private Integer queryId;
	private Integer imageId;
	private Integer similarityRank;

	// Constructors

	/** default constructor */
	public ImageResultInfo() {
	}

	/** full constructor */
	public ImageResultInfo(Integer queryId, Integer imageId) {
		this.queryId = queryId;
		this.imageId = imageId;
	}

	// Property accessors

	public Integer getResultId() {
		return this.resultId;
	}

	public Integer getSimilarityRank() {
		return similarityRank;
	}

	public void setSimilarityRank(Integer similarityRank) {
		this.similarityRank = similarityRank;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getQueryId() {
		return this.queryId;
	}

	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

}