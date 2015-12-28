package com.jianla.ng.solr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jianla.ng.solr.model.News;

public interface NewsService {
	
	News saveOrUpdate(News news);
	
	void saveOrUpdate(List<News> newsList);
	
	void delete(String id);
	
	/**
	 * 根据搜索词查询
	 * 
	 * @param terms 搜索词
	 * @param page 分页器
	 * @return  搜索分页结果
	 */
	Page<News> query(String terms,Pageable page);
	
	/**
	 * 根据关键词查询
	 * 
	 * @param keyword 关键词
	 * @param page 分页器
	 * @return  搜索分页结果
	 */
	Page<News> queryByKeyword(String keyword,Pageable page);

	/**
	 * 根据模型中的条件查询
	 * 
	 * @param news 模型
	 * @param page 分页器
	 * @return  搜索分页结果
	 */
	Page<News> query(News news,Pageable page);

	Page<News> queryForHighlight(News news, Pageable page);
}
