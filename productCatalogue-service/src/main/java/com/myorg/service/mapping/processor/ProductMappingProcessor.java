package com.myorg.service.mapping.processor;

import java.util.ArrayList;
import java.util.List;

import com.myorg.entity.ProductTo;
import com.myorg.infra.components.JPAEntity;
import com.myorg.mapping.ProductModelToEntityMapper;
import com.myorg.model.impl.Product;

public class ProductMappingProcessor {

	public List<JPAEntity> createEntityFromModel(Product product) {
		List<JPAEntity> entityList = new ArrayList<JPAEntity>();
		ProductTo productTo = ProductModelToEntityMapper.INSTANCE.ModelToProductEntity(product);
		entityList.add(productTo);
		return entityList;
	}


}
