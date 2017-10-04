package com.rrt.adp.model;

public class ObjectFile extends DBModel {
	
	private String objectId;
	private String objectAttr;
	private int fileIndex;
	private String fileName;
	private String fileUrl;
	
	public String buildFullFileName(){
		if(null==this.objectId||null==this.objectAttr){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(this.objectId);
		sb.append("/");
		sb.append(this.objectAttr);
		sb.append("/");
		sb.append(this.fileIndex);
		sb.append("/");
		sb.append(this.fileName);
		return sb.toString();
	}
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectAttr() {
		return objectAttr;
	}
	public void setObjectAttr(String objectAttr) {
		this.objectAttr = objectAttr;
	}
	public int getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(int fileIndex) {
		this.fileIndex = fileIndex;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	@Override
	public String toString() {
		return "ObjectFile [objectId=" + objectId + ", objectAttr=" + objectAttr + ", fileIndex=" + fileIndex
				+ ", fileName=" + fileName + ", fileUrl=" + fileUrl + ", id=" + id + ", toString()=" + super.toString()
				+ "]";
	}
	
}
