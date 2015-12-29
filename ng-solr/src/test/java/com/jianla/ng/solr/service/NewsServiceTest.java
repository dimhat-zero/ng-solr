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
		news.setTitle("科比20分15铁林书豪13+6 沃克轰38分黄蜂胜湖人");
		news.setContent("北京时间12月29日，2015-16赛季NBA常规赛继续进行，湖人背靠背做客夏洛特挑战黄蜂。全场打完，湖人98-108不敌黄蜂，遭遇4连败，而黄蜂则取得2连胜。全场具体比分：26-31、32-27、23-27、17-23（湖人在前）。");
		news.setPublishOrgName("搜狐体育");
		news.setPublisherName("波洛");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("数据","投篮点","实录"));
		newsList.add(news);
		
		news=new News();
		news.setId("2");
		news.setTitle("科比2分钟4铁葬送比赛 掀翻林书豪玩命倒地救球");
		news.setContent("科比在本赛季真正的巡演多数来自于东部，因为东部都是一年交手两次，所以客场之旅科比才算是真的告别演出。北京时间2015年12月29日，湖人客场挑战黄蜂，他全场20投5中拿下20分4篮板2助攻，最终湖人以98-108负于黄蜂。");
		news.setPublishOrgName("搜狐体育");
		news.setPublisherName("寒少");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("数据","投篮点","实录"));
		newsList.add(news);
		
		news=new News();
		news.setId("3");
		news.setTitle("霍华德明夏五大归宿：纽约居首 留火箭仍存可能");
		news.setContent("新季，尼克斯摆脱了去年的烂队形象，逐渐回归到季后赛争夺的行列之中。在内线，球队拥有了极具天赋的潜力新秀波尔津吉斯；在其他位置上，纽约的队员也极具拼劲儿。但如果未来，尼克斯想要争夺冠军，他们还需要对阵容进行补充，而霍华德或许正是值得他们考虑的对象。尽管目前来看，魔兽的竞技水准已出现下滑，但是在他健康且全情投入的时段，其攻防两端的威慑力，依旧不容小觑。况且，一旦明年魔兽跳出合同，纽约方面也可以尝试用更低廉的价位与他签约。");
		news.setPublishOrgName("搜狐新闻");
		news.setPublisherName("王晨昊");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("转会","交易"));
		newsList.add(news);
		
		news=new News();
		news.setId("4");
		news.setTitle("前勇士主帅:库里在伤害篮球 小孩都学他投三分");
		news.setContent("北京时间12月27日，据CBS体育报道，勇士队的当家球星斯蒂芬-库里是上赛季的常规赛MVP，也被认为是当今NBA最好的球员。然而他的昔日恩师马克-杰克逊却认为库里伤害了篮球运动，这到底是怎么回事呢？");
		news.setPublishOrgName("网易体育");
		news.setPublisherName("小编003");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("库里","mvp","勇士"));
		newsList.add(news);
		
		news=new News();
		news.setId("5");
		news.setTitle("首节战报：三巨头联手力战群雄 骑士34-30太阳");
		news.setContent("一开场两队立马进入到对攻战当中，乐福在仅仅2分18秒里就连斩5分，和他对位的布克也是当仁不让，包揽了太阳前8分中的6分，紧紧咬住比分。德拉和詹姆斯相继开火，随着杰弗森三分命中，骑士打出9-2的攻击波重新拉开分差。缺少了布莱德索的太阳外线只能依靠奈特来维持攻势，好不容易钱德勒和特里托维奇各自进账把差距缩小到只剩1分。琼斯在还剩26.9秒时又是三分命中。钱德勒最后造成犯规遗憾的是两罚不中，最终骑士首节以34-30领先太阳。乐福和詹姆斯首节均拿到5分，欧文7分。");
		news.setPublishOrgName("腾讯体育");
		news.setPublisherName("小编002");
		news.setPublishTime(new Date());
		news.setTypeName("体育新闻");
		news.setKeywords(Arrays.asList("战报","数据","骑士"));
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
		Page<News> newsList = newsService.query("湖人", new PageRequest(0, 3));
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
		String keyword="数据";
		Page<News> newsList = newsService.queryByKeyword(keyword, new PageRequest(0, 3));
		print(newsList);
	}
	
	@Test
	public void testQueryRelated(){
		List<News> queryRelated = newsService.queryRelated("2", 5);
		for(News news:queryRelated){
			logger.info(news);
		}
	}
}
