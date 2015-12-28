package com.jianla.ng.solr.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.jianla.ng.solr.model.Product;
import com.jianla.ng.solr.model.SearchableProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductServiceTest {

	static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    private Logger    logger = Logger.getLogger(ProductServiceTest.class);
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void initData(){
    	List<Product> products=new ArrayList<>();
    	Product product1=new Product();
		products.add(product1);
		Product product2=new Product();
		products.add(product2);
		Product product3=new Product();
		products.add(product3);
		Product product4=new Product();
		products.add(product4);
		Product product5=new Product();
		products.add(product5);
		Product product6=new Product();
		products.add(product6);
		//1
		product1.setId("1");
		product1.setName("多兰之刃");
		product1.setDescription("装备： +80 最大生命值 +10 攻击 +3% 生命偷取");
		product1.setMinPrice(238f);
		product1.setMaxPrice(475f);
		product1.setCreateTime(new Date());
		product1.setOrgId(1000L);
		product1.setOrgName("lol官方商店");
		product1.setOrgitems(Arrays.asList("生命值","攻击力","吸血"));
		product1.setPtype("攻击类");
		product1.setType("基本装备");
		product1.setAvailable(true);
		//2
		product2.setId("2");
		product2.setName("多兰之盾");
		product2.setDescription("装备： +120 最大生命值 +10 护甲 +8 生命回复/5秒");
		product2.setMinPrice(238f);
		product2.setMaxPrice(475f);
		product2.setCreateTime(new Date());
		product2.setOrgId(1000L);
		product2.setOrgName("lol官方商店");
		product2.setOrgitems(Arrays.asList("生命值","生命回复","护甲值"));
		product2.setPtype("防御类");
		product2.setType("基本装备");
		product2.setAvailable(true);
		//3
		product3.setId("3");
		product3.setName("多兰之戒");
		product3.setDescription("装备： +80最大生命值 +5 法力回复/5秒 +15 法术伤害");
		product3.setMinPrice(238f);
		product3.setMaxPrice(475f);
		product3.setCreateTime(new Date());
		product3.setOrgId(1000L);
		product3.setOrgName("lol官方商店");
		product3.setOrgitems(Arrays.asList("生命值","魔法回复","魔法伤害"));
		product3.setPtype("攻击类");
		product3.setType("基本装备");
		product3.setAvailable(true);
		//4
		product4.setId("4");
		product4.setName("无尽之刃");
		product4.setDescription("装备： +80攻击 +25% 暴击几率 被动(唯一)：将基础暴击伤害从200%提高为250%");
		product4.setMinPrice(2681f);
		product4.setMaxPrice(3830f);
		product4.setCreateTime(new Date());
		product4.setOrgId(1001L);
		product4.setOrgName("lol合成商店");
		product4.setOrgitems(Arrays.asList("攻击力","暴击"));
		product4.setPtype("攻击类");
		product4.setType("合成装备");
		product4.setAvailable(true);
		//5
		product5.setId("5");
		product5.setName("生命药水");
		product5.setDescription("点击使用： 15秒内恢复150点生命值");
		product5.setMinPrice(24f);
		product5.setMaxPrice(35f);
		product5.setCreateTime(new Date());
		product5.setOrgId(1002L);
		product5.setOrgName("lol消耗品商店");
		product5.setOrgitems(Arrays.asList("生命回复","消耗品"));
		product5.setPtype("消耗品");
		product5.setType(null);
		product5.setAvailable(true);
		//6
		product6.setId("6");
		product6.setName("血色之刃");
		product6.setDescription("装备： +60攻击伤害 +15%生命偷取 被动(唯一)：每次攻击提升5点攻击伤害和1%生命偷取，持续4秒，最多叠加7次");
		product6.setMinPrice(2030f);
		product6.setMaxPrice(2900f);
		product6.setCreateTime(new Date());
		product6.setOrgId(1003L);
		product6.setOrgName("lol极地大乱斗商店");
		product6.setOrgitems(Arrays.asList("攻击力","生命偷取"));
		product6.setPtype("攻击装备");
		product6.setType("合成装备");
		product6.setAvailable(false);
		
		//begin index
		productService.saveOrUpdate(products);
		logger.info("初始化数据完成");
    }
    
    //测试打印
    private void printPage(Page<Product> products){
    	int totalPages = products.getTotalPages();
		long totalElements = products.getTotalElements();
		int number=products.getNumber();
		int numberOfElements = products.getNumberOfElements();
		logger.info("总共找到："+totalElements+"条记录，共有"+totalPages+"页,当前页："+number+",当前页个数："+numberOfElements);
		for(Product pro:products.getContent()){
			logger.info(pro);
		}
    }
    
    /**
     * 无搜索条件查询测试  
     */
    @Test
    public void testPageQuery(){
    	Product product=new Product();
		Pageable page=new PageRequest(0, 3);
		Page<Product> products= productService.find(product, page);
		printPage(products);
    }
    
    /**
     * 测试模型的过滤器查询（精确查询）
     */
    @Test
    public void testFilterQuery1(){
    	Product product=new Product();
    	product.setType("基本装备");
    	product.setPtype("攻击类");
		Pageable page=new PageRequest(0, 3);
		Page<Product> products= productService.find(product, page);
		printPage(products);
    }
    @Test
    public void testFilterQuery2(){
    	Product product=new Product();
    	product.setId("1");
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    @Test
    public void testFilterQuery3(){
    	Product product=new Product();
    	product.setOrgId(1000L);
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    /**
     * 测试模型的分词查询，默认是AND
     */
    /**
     * ik会自动分词，计算score排序
     */
    @Test
    public void testQuery(){
    	Product product=new Product();
    	product.setName("药水生命我就不服了多");
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    @Test
    public void testQuery2(){
    	Product product=new Product();
    	product.setDescription("生命值 法术");
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    /**
     * string必须都满足  
     */
    @Test
    public void testQuery3(){
    	Product product=new Product();
    	product.setOrgName("lol 商店");
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    /**
     * 测试list，只要满足一个即可
     */
    @Test
    public void testQuery4(){
    	Product product=new Product();
    	product.setOrgitems(Arrays.asList("攻击力","暴击"));
		Page<Product> products= productService.find(product);
		printPage(products);
    }
    /**
     * 测试字符串的分词查询 OR查询
     */
    @Test
    public void testStrQuery(){
    	Page<Product> products = productService.find("多兰 生命 攻击");
    	printPage(products);
    }
    
    @Test
    public void testFacetQuery(){
    	Map<String, Long> map = productService.facetQuery(SearchableProduct.PTYPE_FIELD);
    	Iterator entries = map.entrySet().iterator();  
    	while(entries.hasNext()){
    		Map.Entry entry = (Map.Entry)entries.next();
    		logger.info(entry.getKey()+":"+entry.getValue());
    	}
    }
}
