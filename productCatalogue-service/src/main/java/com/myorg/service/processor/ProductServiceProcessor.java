package com.myorg.service.processor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.data.repo.ProductRepository;
import com.myorg.entity.ProductTo;
import com.myorg.infra.components.JPAEntity;
import com.myorg.infra.data.repo.GenericDaoJpaImpl;
import com.myorg.infra.exception.DataNotFoundException;
import com.myorg.infra.service.processor.ServiceProcessor;
import com.myorg.infra.util.DateRoutine;
import com.myorg.mapping.ProductModelToEntityMapper;
import com.myorg.model.impl.Product;
import com.myorg.service.mapping.processor.ProductMappingProcessor;

public class ProductServiceProcessor implements ServiceProcessor<Product> {
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	private ProductMappingProcessor mappingProcessor;
	@Autowired
	GenericDaoJpaImpl genericDaoJpaImpl;

	@Transactional
	@Override
	public void processPost(Product product) throws Throwable {

		List<JPAEntity> entityListResponse = new ArrayList<JPAEntity>();
		Integer maxClientSegNumber = 0;
		if (product.getProdId()!= 0) {
			ProductTo partTo = prodRepo.findProductById(product.getProdId());
		}
		
	}

	public List<JPAEntity> systemDefinedFields( ProductTo productTo)
			throws ParseException {

		List<JPAEntity> entityList = new ArrayList<JPAEntity>();
		
		entityList.add(productTo);
		
		return entityList;

	}

	@Override
	public boolean checkIfEntityExists(Product product) {
		ProductTo partTo = prodRepo.findProductById(product.getProdId());
		if (partTo == null) {

			throw new DataNotFoundException("no.product.exists");
		}
	

	

		return true;
	}

	@Override
	public Product processGetById(Product product) {
		ProductTo partTo = prodRepo.findProductById(Integer.valueOf(product.getProdId()));
		return product;
	}
	
	

	@Transactional
	@Override
	public void processUpdate(Product product) throws ParseException {
		List<JPAEntity> entityListResponse = new ArrayList<JPAEntity>();
		ProductTo productTo = ProductModelToEntityMapper.INSTANCE.ModelToProductEntity(product);
		for (JPAEntity entity : entityListResponse) {
			genericDaoJpaImpl.update(entity);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processDelete(Product product) {
		ProductTo partTo = prodRepo.findProductById(Integer.valueOf(product.getProdId()));
		List<JPAEntity> entityList = mappingProcessor.createEntityFromModel(product);
		for (JPAEntity entity : entityList) {
			genericDaoJpaImpl.delete(entity);
		}
	}

	@Override
	public List<Product> processSearchList(Product product) {
	
		 int prodId=0;;
		 String name="";
		 String type="";
		 BigDecimal price=new BigDecimal("0.00");
		 List<Product> productListResp = new ArrayList<Product>();
		int cltseqnum = 0;
		
		
		 List<ProductTo> productList=prodRepo.productListByCriteria(product.getProdId(), product.getType());

		Date effectiveDate, birthDate, expirationDate, dateOfBirth;

		for (ProductTo productTo : productList) {
				
			product = new Product(prodId,name,type,price);
			productListResp.add(product);

		}
		return productListResp;
	}

}
