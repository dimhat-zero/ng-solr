package com.jianla.ng.solr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;

import com.jianla.ng.solr.model.Product;

public interface ProductRepository extends CrudRepository<Product, String>{

	//Page<Product> find(Product product, Pageable page, SortableProduct sort, Direction order);


	/**
	 * 输入关键词后进行查询
	 * 
	 * @param product 输入的关键词
	 * @param page 分页信息PageRequest
	 * @return  分页查询结果
	 */
	Page<Product> find(Product product, Pageable page, Sort sort);

	Page<Product> find(String searchTerm, Pageable page, Sort sort);

	Page<FacetFieldEntry> getFacetByFieldName(String fieldName);

}
