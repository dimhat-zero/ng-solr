package com.jianla.ng.solr.model;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Product implements SearchableProduct {

	@Field(ID_FIELD)
	private String id;//产品主键
	
	/**
	 * 机构信息
	 */
	@Field(ORG_ID_FIELD)
	private Long orgId;
	@Field(ORG_NAME_FIELD)
	private String orgName;
	@Field(ORG_QQ_FIELD)
	private String orgQQ;
	
	/**
	 * 商品信息
	 */
	@Field(NAME_FIELD)
	private String name;//title
	@Field(ICON_FIELD)
	private String iconUrl;
	@Field(DESCRIPTION_FIELD)
	private String description;//intro
	@Field(ORGITEM_FIELD)
	private List<String> orgitems;//orgitem的列表
	@Field(MIN_PRICE_FIELD)
	private Float minPrice;//最小价格
	@Field(MAX_PRICE_FIELD)
	private Float maxPrice;//最大价格
	@Field(TYPE_FIELD)
	private String type;//服务二级类别
	@Field(PTYPE_FIELD)
	private String ptype;//服务一级类别
	@Field(USAGE_FIELD)
	private String usage;//电商平台
	@Field(CREATE_TIME_FIELD)
	private Date createTime;//创建时间
	@Field(AVAILABLE_FIELD)
	private Boolean available;//只有上架状态才可用(checkStatus=1&status=1)
	
	/**
	 * 评价指标
	 */
	@Field(POPULARITY_FIELD)
	private Integer popularity;//浏览数
	@Field(SALES_VOLUME_FIELD)
	private Integer salesVolume;//销量
	@Field(INSPECTION_COUNT_FIELD)
	private Integer inspectionCount;//报检数
	@Field(SERVICE_SCORE_FIELD)
	private Double serviceScore;//服务评分
	@Field(ORDER_NUMBER_FIELD)
	private Integer orderNumber;//订单数量
	
	/*
	 * getter and setter
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgQQ() {
		return orgQQ;
	}
	public void setOrgQQ(String orgQQ) {
		this.orgQQ = orgQQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getOrgitems() {
		return orgitems;
	}
	public void setOrgitems(List<String> orgitems) {
		this.orgitems = orgitems;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Integer getInspectionCount() {
		return inspectionCount;
	}
	public void setInspectionCount(Integer inspectionCount) {
		this.inspectionCount = inspectionCount;
	}
	public Double getServiceScore() {
		return serviceScore;
	}
	public void setServiceScore(Double serviceScore) {
		this.serviceScore = serviceScore;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Product [id=").append(id).append(", orgId=").append(orgId).append(", orgName=").append(orgName)
				.append(", orgQQ=").append(orgQQ).append(", name=").append(name).append(", iconUrl=").append(iconUrl)
				.append(", description=").append(description).append(", orgitems=")
				.append(orgitems != null ? orgitems.subList(0, Math.min(orgitems.size(), maxLen)) : null)
				.append(", minPrice=").append(minPrice).append(", maxPrice=").append(maxPrice).append(", type=")
				.append(type).append(", ptype=").append(ptype).append(", usage=").append(usage).append(", createTime=")
				.append(createTime).append(", available=").append(available).append(", popularity=").append(popularity)
				.append(", salesVolume=").append(salesVolume).append(", inspectionCount=").append(inspectionCount)
				.append(", serviceScore=").append(serviceScore).append(", orderNumber=").append(orderNumber)
				.append("]");
		return builder.toString();
	}
	
}
