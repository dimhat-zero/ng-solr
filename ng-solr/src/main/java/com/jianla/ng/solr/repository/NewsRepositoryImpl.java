package com.jianla.ng.solr.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.expression.Operation;
import org.springframework.util.CollectionUtils;

import com.jianla.ng.solr.model.News;
import com.jianla.ng.solr.model.SearchableNews;
import com.jianla.ng.solr.util.RepositoryUtils;

public class NewsRepositoryImpl extends SimpleSolrRepository<News, String> implements NewsRepository {

	private void addQuery(Query query, News news) {
		// add query
		if (!StringUtils.isEmpty(news.getTitle())) {
			RepositoryUtils.addCriteriaIK(query, SearchableNews.TITLE_FIELD, news.getTitle());
		}
		if (!StringUtils.isEmpty(news.getContent())) {
			RepositoryUtils.addCriteriaIK(query, SearchableNews.CONTENT_FIELD, news.getContent());
		}
	}

	private void addFilterQuery(Query query, News news) {
		// add filter
		FilterQuery filterQuery = new SimpleFilterQuery();
		if (!StringUtils.isEmpty(news.getId())) {
			filterQuery.addCriteria(new Criteria(SearchableNews.ID_FIELD).is(news.getId()));
		}
		if (!CollectionUtils.isEmpty(news.getKeywords())) {
			filterQuery.addCriteria(new Criteria(SearchableNews.KEYWORDS_FIELD).is(news.getKeywords()));
		}
		if (!StringUtils.isEmpty(news.getTypeName())) {
			filterQuery.addCriteria(new Criteria(SearchableNews.TYPE_FIELD).is(news.getTypeName()));
		}
		if (!StringUtils.isEmpty(news.getPublishOrgName())) {
			filterQuery.addCriteria(new Criteria(SearchableNews.PUBLISH_ORGNAME_FIELD).is(news.getPublishOrgName()));
		}
		if (!StringUtils.isEmpty(news.getPublisherName())) {
			filterQuery.addCriteria(new Criteria(SearchableNews.PUBLISHER_NAME_FIELD).is(news.getPublisherName()));
		}
		if (news.getAvailable() != null) {
			filterQuery.addCriteria(new Criteria(SearchableNews.AVAILABLE_FIELD).is(news.getAvailable()));
		}
		query.addFilterQuery(filterQuery);
	}

	@Override
	public Page<News> find(News news, Pageable page, Sort sort) {
		Query query = new SimpleQuery(new SimpleStringCriteria("*:*")).setPageRequest(page).addSort(sort);
		query.setDefaultOperator(Operator.OR);
		addQuery(query, news);
		addFilterQuery(query, news);
		return getSolrOperations().queryForPage(query, News.class);
	}
	
	@Override
	public Page<News> find(String terms, Pageable page, Sort sort) {
		Query query = new SimpleQuery(new SimpleStringCriteria("*:*")).setPageRequest(page).addSort(sort);
		if(!StringUtils.isEmpty(terms)){
			Criteria conditions = new Criteria(SearchableNews.TITLE_FIELD).is(terms).or(new Criteria(SearchableNews.CONTENT_FIELD).is(terms));
			query.addCriteria(conditions);
		}
		//过滤可用的
		FilterQuery filterQuery = new SimpleFilterQuery();
		filterQuery.addCriteria(new Criteria(SearchableNews.AVAILABLE_FIELD).is(Boolean.TRUE));
		query.addFilterQuery(filterQuery);
		return getSolrOperations().queryForPage(query, News.class);
	}

	@Override
	public HighlightPage<News> findHighlight(News news, Pageable page, Sort sort) {
		SimpleHighlightQuery highlightQuery = new SimpleHighlightQuery(new SimpleStringCriteria("*:*")).setPageRequest(
				page).addSort(sort);
		addQuery(highlightQuery, news);
		addFilterQuery(highlightQuery, news);
		highlightQuery.setHighlightOptions(new HighlightOptions());
		HighlightPage<News> highLightPage = getSolrOperations().queryForHighlightPage(highlightQuery, News.class);
		return highLightPage;
	}

	@Override
	public List<News> findRelated(String docId, int count) {
		Query query = new SimpleQuery(new SimpleStringCriteria("id:"+docId)).setPageRequest(new PageRequest(0, count));
		query.setRequestHandler("/mlt");
		return getSolrOperations().queryForPage(query, News.class).getContent();
	}

}
