#solr搜索项目 -- open version
操作solr使用spring-data-solr

##product 商品搜索


##new 新闻搜索


##boost打分
@Boost注解

```
Criteria webpage= new Criteria("category").is("webpage").boost((float) 1000);
Criteria document= new Criteria("category").is("document").boost((float) 2);
Criteria combined= webpage.or( document);
```

```
Criteria criteria = new SimpleStringCriteria("(visibility:(visible^1000) OR visibility:(draft^500) OR visibility:(archived))");
```


##Highlight显示
@Highlight注解

HighlightPage<T> queryForHighlightPage方法
```
for(HighlightEntry<News> highlightEntry:highlightList.getHighlighted()){
	News newsEntity = highlightEntry.getEntity();//真实对象
	for(Highlight highlight:highlightEntry.getHighlights()){
		if(highlight.getField().getName().equals("content")){
			newsEntity.setContent(highlight.getSnipplets().get(0));
		}else if(highlight.getField().getName().equals("title")){
			newsEntity.setTitle(highlight.getSnipplets().get(0));
		}
	}
}
```