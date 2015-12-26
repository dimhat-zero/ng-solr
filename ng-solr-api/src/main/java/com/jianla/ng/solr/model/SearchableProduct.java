package com.jianla.ng.solr.model;


public interface SearchableProduct {
	/** 产品主键 */
	String ID_FIELD = "id";
	/** 电商平台 */
	String USAGE_FIELD = "usage";
	/** 服务类别 */
	String TYPE_FIELD = "type";
	String PTYPE_FIELD = "ptype";
	/** 机构信息 */
	String ORG_ID_FIELD = "orgId";
	String ORG_NAME_FIELD = "orgName";
	String ORG_QQ_FIELD = "orgQQ";
	/** 商品信息 */
	String NAME_FIELD = "name";//title
	String ICON_FIELD = "iconUrl";
	String DESCRIPTION_FIELD = "description";//intro
	String ORGITEM_FIELD="orgitems";//orgitem的列表
	String MIN_PRICE_FIELD="minPrice";//最小价格
	String MAX_PRICE_FIELD="maxPrice";//最大价格
	String CREATE_TIME_FIELD="createTime";//创建时间
	String AVAILABLE_FIELD="available";//只有上架状态才可用(checkStatus=1&status=1)
	//评价指标
	String POPULARITY_FIELD = "popularity";//浏览数
	String SALES_VOLUME_FIELD = "salesVolume";//销量
	String INSPECTION_COUNT_FIELD="inspectionCount";//报检数
	String SERVICE_SCORE_FIELD="serviceScore";//服务评分
	String ORDER_NUMBER_FIELD="orderNumber";//订单数量
}
