package com.jianla.ng.solr.model;

public enum SortableNews {

	PUBLISH_TIME(SearchableNews.PUBLISH_TIME_FIELD);
	
	String fieldName;

	private SortableNews(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getName() {
		return fieldName;
	}
	
	
}
