package com.jianla.ng.solr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.HighlightPage;

import com.jianla.ng.solr.model.News;

public interface NewsRepository  extends CrudRepository<News, String>{

	/**
	 * 新闻搜索
	 * @param news 搜索模型
	 * @param page 分页器
	 * @param sort 排序器
	 * @return 分页搜索结果
	 */
	Page<News> find(News news,Pageable page,Sort sort);
	
	HighlightPage<News> findHighlight(News news,Pageable page,Sort sort);
}
