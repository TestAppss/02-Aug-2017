package com.myorg.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.myorg.entity.ProductTo;
import com.myorg.model.impl.Product;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductModelToEntityMapper {

	ProductModelToEntityMapper INSTANCE = Mappers.getMapper(ProductModelToEntityMapper.class);
	
	@Mapping(target = "prodId", source = "product.prodId")
	@Mapping(target = "name", source = "product.name")
	@Mapping(target = "type", source = "product.type")
	@Mapping(target = "price", source = "product.price")
	
	public ProductTo ModelToProductEntity(Product product);

	
	
}
