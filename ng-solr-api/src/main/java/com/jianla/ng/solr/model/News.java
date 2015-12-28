package com.jianla.ng.solr.model;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class News implements SearchableNews {

	@Field(ID_FIELD)
	private String id;
	@Field(TITLE_FIELD)
	private String title;
	@Field(CONTENT_FIELD)
	private String content;
	@Field(KEYWORDS_FIELD)
	private List<String> keywords;
	@Field(PUBLISH_TIME_FIELD)
	private Date publishTime;
	@Field(CHECK_TIME_FIELD)
	private Date checkTime;
	@Field(TYPE_FIELD)
	private String typeName;
	@Field(PUBLISH_ORGNAME_FIELD)
	private String publishOrgName;
	@Field(PUBLISHER_NAME_FIELD)
	private String publisherName;
	@Field(AVAILABLE_FIELD)
	private Boolean available=Boolean.TRUE;//status=3可用，默认可用
	
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPublishOrgName() {
		return publishOrgName;
	}
	public void setPublishOrgName(String publishOrgName) {
		this.publishOrgName = publishOrgName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("News [id=").append(id).append(", title=").append(title).append(", content=").append(content)
				.append(", keywords=")
				.append(keywords != null ? keywords.subList(0, Math.min(keywords.size(), maxLen)) : null)
				.append(", publishTime=").append(publishTime).append(", checkTime=").append(checkTime)
				.append(", typeName=").append(typeName).append(", publishOrgName=").append(publishOrgName)
				.append(", publisherName=").append(publisherName).append(", available=").append(available).append("]");
		return builder.toString();
	}
	
	
}
