package com.jianla.ng.solr.util;

import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;

public class RepositoryUtils {

	public static void addCriteriaIK(Query query,String prop,String searchTerm){
		String[] words = searchTerm.split(" +");
		for(String word:words){
			query.addCriteria(new Criteria(prop).is(word));
		}
	}
	public static void addCriteria(Query query,String prop,String searchTerm){
		String[] words = searchTerm.split(" +");
		for(String word:words){
			query.addCriteria(new Criteria(prop).contains(word));
		}
	}
	
	public static void addCriteriaIK(Query query,String prop,String searchTerm,float boost){
		String[] words = searchTerm.split(" +");
		for(String word:words){
			query.addCriteria(new Criteria(prop).is(word).boost(boost));
		}
	}
	public static void addCriteria(Query query,String prop,String searchTerm,float boost){
		String[] words = searchTerm.split(" +");
		for(String word:words){
			query.addCriteria(new Criteria(prop).contains(word).boost(boost));
		}
	}
	
}
