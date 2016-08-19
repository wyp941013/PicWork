package com.work.entity;

/**
 * ImageUploadView entity. @author MyEclipse Persistence Tools
 */

public class ImageUploadView implements java.io.Serializable {

//	// Fields
//
//	private ImageUploadViewId id;
//
//	// Constructors
//
//	/** default constructor */
//	public ImageUploadView() {
//	}
//
//	/** full constructor */
//	public ImageUploadView(ImageUploadViewId id) {
//		this.id = id;
//	}
//
//	// Property accessors
//
//	public ImageUploadViewId getId() {
//		return this.id;
//	}
//
//	public void setId(ImageUploadViewId id) {
//		this.id = id;
//	}
	// Fields

		private Integer editorUserId;
		private String userName;
		private long num;

		// Constructors


		// Property accessors

		public Integer getEditorUserId() {
			return this.editorUserId;
		}

		public void setEditorUserId(Integer editorUserId) {
			this.editorUserId = editorUserId;
		}

		public String getUserName() {
			return this.userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public long getNum() {
			return this.num;
		}

		public void setNum(long num) {
			this.num = num;
		}


}