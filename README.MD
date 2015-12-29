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

##相关度查询
方法一：MoreLikeThisComponent
```
//mlt在查询时，打开/关闭 MoreLikeThisComponent 的布尔值
query.setParam("mlt", "true"); 
//fl 需要返回的字段
query.setParam("fl", "score,ask");
//mtl.fl 根据哪些字段判断相似度
query.setParam("mlt.fl", "ask");  
//mlt.mintf 最小分词频率，在单个文档中出现频率小于这个值的词将不用于相似判断
query.setParam("mlt.mintf", "1");
//mlt.mindf 最小文档频率，所在文档的个数小于这个值的词将不用于相似判断
query.setParam("mlt.mindf", "1");  
```

方法二：MoreLikeThisHandler 
首先在 solrconfig.xml 中配置 MoreLikeThisHandler 
```
<requestHandler name="/mlt" class="solr.MoreLikeThisHandler"> 
	<lst name="defaults">
		<str name="mlt">true</str>
		<str name="mlt.fl">title,content</str>
		<str name="mlt.mindf">1</str>
		<str name="mlt.mintf">1</str>
	</lst>
</requestHandler>
```