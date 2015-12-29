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

	/**
	 * 根据模型中的条件高亮查询
	 * 
	 * @param news 模型
	 * @param page 分页器
	 * @return  高亮搜索分页结果
	 */
	Page<News> queryForHighlight(News news, Pageable page);
	
	/**
	 * 查询相关度最高的几个文档
	 * 
	 * @param docId 文档id
	 * @param count 相关文档个数
	 * @return 相关文档列表
	 */
	List<News> queryRelated(String docId,int count);
}
