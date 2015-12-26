package com.jianla.ng.solr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;

import com.jianla.ng.solr.model.Product;
import com.jianla.ng.solr.model.SortableProduct;
import com.jianla.ng.solr.repository.ProductRepository;

/**
 * 产品服务实现类
 * 
 * @author dimhat
 * @date 2015年12月25日 上午11:56:10
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {

    private ProductRepository productDao;
    
    public static final int DEFAULT_PAGE  =  0;
    public static final int DEFAULT_SIZE = 10;
	/**
	 * 默认的分页
	 */
	private PageRequest getDefaultPage(){
		return new PageRequest(DEFAULT_PAGE, DEFAULT_SIZE);
	}
	
	//按时间降序
	public static final Sort SORT_BY_TIME_DESC = new Sort(Sort.Direction.DESC, SortableProduct.CREATE_TIME.getName());
	//按评分降序
	public static final Sort SORT_BY_SCORE_DESC = new Sort(Sort.Direction.DESC,"score");
	
	/**
	 * 默认的排序
	 */
	private Sort getDefaultSort(){
		return SORT_BY_TIME_DESC;
	}
	
	@Override
	public Product saveOrUpdate(Product product){
		return productDao.save(product);
	}
	
	@Override
	public void saveOrUpdate(List<Product> products){
		productDao.save(products);
	}
	
	@Override
	public void delete(String id){
		productDao.delete(id);
	}
	
	@Override
	public Page<Product> find(Product product,Pageable page){
		return productDao.find(product,page,getDefaultSort());
	}
	@Override
	public Page<Product> find(Product product) {
		return find(product,getDefaultPage());
	}
	
	
	
	@Override
	public Page<Product> find(Product product,Pageable page,SortableProduct prop,Sort.Direction order){
		return productDao.find(product,page,new Sort(order,prop.getName()));
	}

	public void setProductDao(ProductRepository productDao) {
		this.productDao = productDao;
	}

	/**
	 * 按关键词搜索
	 */
	
	
	@Override
	public Page<Product> find(String searchTerm) {
		return productDao.find(searchTerm,getDefaultPage(),getDefaultSort());
	}

	@Override
	public Page<Product> find(String searchTerm,Pageable page) {
		return productDao.find(searchTerm,page,getDefaultSort());
	}

	@Override
	public Page<Product> find(String searchTerm,Pageable page,Sort sort) {
		return productDao.find(searchTerm,page,sort);
	}

	@Override
	public Map<String,Long> facetQuery(String fieldName) {
		Page<FacetFieldEntry> page = productDao.getFacetByFieldName(fieldName);
		Map<String,Long> map=new HashMap<>();
		for(FacetFieldEntry entry:page.getContent()){
			//key是字段名，value是导航值，valueCount是这个导航值的个数
			map.put(entry.getValue(), entry.getValueCount());
		}
		return map;
	}
}
