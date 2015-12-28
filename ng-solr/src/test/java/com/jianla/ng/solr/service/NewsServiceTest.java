package com.jianla.ng.solr.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.jianla.ng.solr.model.News;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class NewsServiceTest {

	static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }
	
	private Logger logger=Logger.getLogger(NewsServiceTest.class);
	
	@Autowired
	private NewsService newsService;
	
	@Test
	public void initData(){
		List<News> newsList=new ArrayList<>();
		News news=new News();
		news.setId("1");
		news.setTitle("小伙注册芈月商标 只因读小说时突发奇想");
		news.setContent("从开拍就争议不断的《芈月传》播出过半，引起的各种热门话题不断。当你还在纠结“芈”字怎么读的时候，“芈月”商标已经被人抢注了！注册商标的叫王超，是郑州一位80后的小伙子。许多人想买他的商标注册权，一个上海的老板甚至开价60万。");
		news.setPublishOrgName("新浪娱乐");
		news.setPublisherName("小编001");
		news.setPublishTime(new Date());
		news.setTypeName("娱乐新闻");
		news.setKeywords(Arrays.asList("key1","key2","key3"));
		newsList.add(news);
		
		news=new News();
		news.setId("2");
		news.setTitle("2015年国际十大新闻及人物出炉");
		news.setContent("由光明日报社和中共泉州市委联合主办、中国电信集团公司支持的第十七届“中国国际新闻论坛”年会日前在福建省泉州市举行，与会的全国40余家媒体以无记名投票方式评出2015年国际十大新闻及十大焦点人物。");
		news.setPublishOrgName("搜狐新闻");
		news.setPublisherName("小编001");
		news.setPublishTime(new Date());
		news.setTypeName("时政新闻");
		news.setKeywords(Arrays.asList("key1","key2","key4"));
		newsList.add(news);
		
		news=new News();
		news.setId("3");
		news.setTitle("塔利班否认为抗击“伊斯兰国”与俄罗斯交换信息");
		news.setContent("喀布尔消息：据媒体27日报道，塔利班26日否认为抗击在阿富汗境内的极端组织“伊斯兰国”而与俄罗斯交换信息，并称不在意“伊斯兰国”在阿富汗的影响力。");
		news.setPublishOrgName("搜狐新闻");
		news.setPublisherName("小编002");
		news.setPublishTime(new Date());
		news.setTypeName("时政新闻");
		news.setKeywords(Arrays.asList("key2","key3","key4"));
		newsList.add(news);
		
		news=new News();
		news.setId("4");
		news.setTitle("前勇士主帅:库里在伤害篮球 小孩都学他投三分");
		news.setContent("北京时间12月27日，据CBS体育报道，勇士队的当家球星斯蒂芬-库里是上赛季的常规赛MVP，也被认为是当今NBA最好的球员。然而他的昔日恩师马克-杰克逊却认为库里伤害了篮球运动，这到底是怎么回事呢？");
		news.setPublishOrgName("网易体育");
		news.setPublisherName("小编003");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("key5","key3","key4"));
		newsList.add(news);
		
		news=new News();
		news.setId("5");
		news.setTitle("王思聪当街激吻 绯闻新欢又是嫩模 看旧照也是大变脸");
		news.setContent("王思聪身边又换了新女票还在大街上热吻的新闻瞬间又火了……");
		news.setPublishOrgName("腾讯娱乐");
		news.setPublisherName("小编002");
		news.setPublishTime(new Date());
		news.setTypeName("娱乐新闻");
		news.setKeywords(Arrays.asList("key5","key1","key6"));
		news.setAvailable(true);
		newsList.add(news);
		
		newsService.saveOrUpdate(newsList);
		logger.info("初始化完成");
	}
	
	@Test
	public void testDelete(){
		newsService.delete("1");
		logger.info("删除成功");
	}
	
	private void print(Page<News> page){
		logger.info("总共有"+page.getTotalPages()+"页，共有"+page.getTotalElements()+"条");
		for(News news:page.getContent()){
			logger.info(news);
		}
	}
	@Test
	public void testHighlight(){
		News news=new News();
		news.setContent("新闻");
		Page<News> newsList = newsService.queryForHighlight(news, new PageRequest(0, 10));
		print(newsList);
	}
	
	@Test
	public void testQueryTerms(){
		Page<News> newsList = newsService.query("王思聪", new PageRequest(0, 3));
		print(newsList);
	}
	
	@Test
	public void testQueryModel(){
		News news=new News();
		Page<News> newsList = newsService.query(news,new PageRequest(0, 3));
		print(newsList);
	}
	
	@Test
	public void testQueryByKeyword(){
		String keyword="key3";
		Page<News> newsList = newsService.queryByKeyword(keyword, new PageRequest(0, 3));
		print(newsList);
	}
}
