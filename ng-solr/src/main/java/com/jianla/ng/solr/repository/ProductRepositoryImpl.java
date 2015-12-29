package com.jianla.ng.solr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jianla.ng.solr.model.News;
import com.jianla.ng.solr.model.Product;
import com.jianla.ng.solr.model.SearchableProduct;
import com.jianla.ng.solr.util.RepositoryUtils;

/**
 * 产品repo实现类
 * @author dimhat
 * @date 2015年12月25日 上午11:54:09
 * @version 1.0
 */
public class ProductRepositoryImpl extends SimpleSolrRepository<Product,String> implements ProductRepository {


	/** 
	 * solr搜索product核心方法，page中的sort对象？
	 */
	@Override
	public Page<Product> find(Product product, Pageable page, Sort sort) {
		Query query = new SimpleQuery(new SimpleStringCriteria("*:*")).setPageRequest(page);
		this.addQuery(query,product);//增加分词查询条件
		this.addFilterQuery(query,product);//增加过滤条件
		query.addSort(sort);//增加查询条件
        return getSolrOperations().queryForPage(query, Product.class);
	}
	
	
	/**
	 * 增加查询条件，根据配置 默认是 AND 或 OR
	 */
	private void addQuery(Query query, Product product) {
		if(!StringUtils.isEmpty(product.getOrgName())){//orgname string 
			RepositoryUtils.addCriteria(query,SearchableProduct.ORG_NAME_FIELD,product.getOrgName());
		}
		if(!StringUtils.isEmpty(product.getName())){//name ik
			RepositoryUtils.addCriteriaIK(query, SearchableProduct.NAME_FIELD, product.getName());
		}
		if(!StringUtils.isEmpty(product.getDescription())){//description ik
			RepositoryUtils.addCriteriaIK(query, SearchableProduct.DESCRIPTION_FIELD, product.getDescription());
		}
		if(!CollectionUtils.isEmpty(product.getOrgitems())){//orgitem ik list
			query.addCriteria(new Criteria(SearchableProduct.ORGITEM_FIELD).is(product.getOrgitems()));
		}
	}
	
	
	
	/**
	 * 增加过滤条件，一定是全部符合的
	 */
	private void addFilterQuery(Query query, Product product) {
		FilterQuery filterQuery=new SimpleQuery();
		if(!StringUtils.isEmpty(product.getId())){//id
			filterQuery.addCriteria(new Criteria(SearchableProduct.ID_FIELD).is(product.getId()));
		}
		if(product.getOrgId()!=null){//orgid
			filterQuery.addCriteria(new Criteria(SearchableProduct.ORG_ID_FIELD).is(product.getOrgId()));
		}
		if(!StringUtils.isEmpty(product.getType())){//type
			filterQuery.addCriteria(new Criteria(SearchableProduct.TYPE_FIELD).is(product.getType()));
		}
		if(!StringUtils.isEmpty(product.getPtype())){//ptype
			filterQuery.addCriteria(new Criteria(SearchableProduct.PTYPE_FIELD).is(product.getPtype()));
		}
		if(!StringUtils.isEmpty(product.getUsage())){//usage
			filterQuery.addCriteria(new Criteria(SearchableProduct.USAGE_FIELD).is(product.getUsage()));
		}
		filterQuery.addCriteria(new Criteria(SearchableProduct.AVAILABLE_FIELD).is(Boolean.TRUE));//搜索引擎只找到可用的
		query.addFilterQuery(filterQuery);
	}
	/*
	 * not search field,can not index 不搜索字段只展示不用索引
	 * orgQQ,iconUrl,minPrice,maxPrice,createTime
	 */


	/** 
	 * 常用的多关键词分词查询
	 */
	@Override
	public Page<Product> find(String searchTerm,Pageable page,Sort sort) {
		String[] words = searchTerm.split(" +");
        Criteria conditions = createSearchConditions(words);
        SimpleQuery search = new SimpleQuery(conditions).setPageRequest(page);
        search.addSort(sort);
 
        return getSolrOperations().queryForPage(search, Product.class);
	}
	
	private Criteria createSearchConditions(String[] words) {
		Criteria conditions = null;
        for (String word: words) {//多关键词
            if (conditions == null) {
                conditions = new Criteria("name").is(word).or(new Criteria("description").is(word));
            }
            else {
                conditions = conditions.or(new Criteria("name").is(word)).or(new Criteria("description").is(word));
            }
        }
        return conditions;
	}
	
	@Override
	public Page<FacetFieldEntry> getFacetByFieldName(String fieldName){
		 FacetQuery query = new SimpleFacetQuery(new SimpleStringCriteria("*:*"));//查询
	     query.setFacetOptions(new FacetOptions(fieldName));//设置导航选项
	     FacetPage<Product> facetPage = getSolrOperations().queryForFacetPage(query, Product.class);
	     Page<FacetFieldEntry> facetResultPage = facetPage.getFacetResultPage(fieldName);
	     return facetResultPage; 
	}
   
	@Override
	public List<Product> findRelated(String docId, int count) {
		Query query = new SimpleQuery(new SimpleStringCriteria("id:"+docId)).setPageRequest(new PageRequest(0, count));
		query.setRequestHandler("/mlt");
		return getSolrOperations().queryForPage(query, Product.class).getContent();
	}
}
