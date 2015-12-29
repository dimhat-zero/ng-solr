package com.jianla.ng.solr.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightEntry.Highlight;
import org.springframework.data.solr.core.query.result.HighlightPage;

import com.jianla.ng.solr.model.News;
import com.jianla.ng.solr.model.SortableNews;
import com.jianla.ng.solr.repository.NewsRepository;

public class NewsServiceImpl implements NewsService {
	
	private NewsRepository newsDao;
	
	private static final Sort SORT_BY_PUBLISHTIME_DESC=new Sort(Sort.Direction.DESC,SortableNews.PUBLISH_TIME.getName());
	
	private Sort getDefaultSort(){
		return SORT_BY_PUBLISHTIME_DESC;
	}

	@Override
	public News saveOrUpdate(News news) {
		return newsDao.save(news);
	}
	
	@Override
	public void saveOrUpdate(List<News> newsList) {
		newsDao.save(newsList);
	}

	@Override
	public void delete(String id) {
		newsDao.delete(id);
	}

	@Override
	public Page<News> query(String terms, Pageable page) {
		return newsDao.find(terms, page, getDefaultSort());
	}

	@Override
	public Page<News> queryByKeyword(String keyword, Pageable page) {
		News news=new News();
		news.setKeywords(Arrays.asList(keyword));
		return newsDao.find(news, page, getDefaultSort());
	}

	@Override
	public Page<News> query(News news, Pageable page) {
		return newsDao.find(news, page, getDefaultSort());
	}
	
	@Override
	public Page<News> queryForHighlight(News news,Pageable page){
		HighlightPage<News> highlightList = newsDao.findHighlight(news, page, getDefaultSort());
		for(HighlightEntry<News> highlightEntry:highlightList.getHighlighted()){
			News newsEntity = highlightEntry.getEntity();
			for(Highlight highlight:highlightEntry.getHighlights()){
				if(highlight.getField().getName().equals("content")){
					newsEntity.setContent(highlight.getSnipplets().get(0));//多值，这里只有一个值
				}else if(highlight.getField().getName().equals("title")){
					newsEntity.setTitle(highlight.getSnipplets().get(0));
				}
			}
		}
		return highlightList;
	}
	
	@Override
	public List<News> queryRelated(String docId, int count) {
		return newsDao.findRelated(docId, count);
	}
	
	public void setNewsDao(NewsRepository newsDao) {
		this.newsDao = newsDao;
	}


}
