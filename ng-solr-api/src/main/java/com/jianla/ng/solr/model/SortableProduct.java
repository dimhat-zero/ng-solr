package com.jianla.ng.solr.model;

/**
 * 可排序字段枚举
 * 
 * @author dimhat
 * @date 2015年12月25日 下午3:18:21
 * @version 1.0
 */
public enum SortableProduct {
	
	CREATE_TIME(SearchableProduct.CREATE_TIME_FIELD),
	
	POPULARITY(SearchableProduct.POPULARITY_FIELD),
	
	SALES_VOLUME(SearchableProduct.SALES_VOLUME_FIELD),
	
	INSPECTION_COUNT(SearchableProduct.INSPECTION_COUNT_FIELD),
	
	SERVICE_SCORE(SearchableProduct.SERVICE_SCORE_FIELD),
	
	ORDER_NUMBER(SearchableProduct.ORDER_NUMBER_FIELD);
	
	private final String fieldName;

    private SortableProduct(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getName() {
        return fieldName;
    }
}
