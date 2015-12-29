package com.jianla.ng.solr.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jianla.ng.solr.model.News;
import com.jianla.ng.solr.model.Product;
import com.jianla.ng.solr.model.SortableProduct;

public interface ProductService {

	Product saveOrUpdate(Product product);

	void saveOrUpdate(List<Product> products);

	void delete(String id);
	
	/**
	 * 根据模型进行查询
	 * 
	 * @param product 要搜索的模型
	 * @param page 分页
	 * @param prop 排序字段
	 * @param order 排序顺序
	 * @return  
	 */
	Page<Product> find(Product product);
	Page<Product> find(Product product, Pageable page);
	Page<Product> find(Product product,Pageable page, SortableProduct prop, Direction order);
	
	/**
	 * 根据搜索字符串进行查询
	 * 
	 * @param searchTerm 搜索字符串
	 * @param page 分页信息
	 * @param sort 排序信息
	 * @return 搜索结果
	 */
	Page<Product> find(String searchTerm);
	Page<Product> find(String searchTerm, Pageable page);
	Page<Product> find(String searchTerm, Pageable page, Sort sort);

	Map<String,Long> facetQuery(String ptypeField);

	List<Product> queryRelated(String docId, int count);

}
