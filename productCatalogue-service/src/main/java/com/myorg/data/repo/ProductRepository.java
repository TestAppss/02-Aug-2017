package com.myorg.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myorg.entity.ProductTo;

public interface ProductRepository extends JpaRepository<ProductTo, String> {
	
	@Query("select g from Product g WHERE g.prodId=:prodId")
	ProductTo findProductById(@Param("prodId") int i);

	// To search prodcut Id based on Product type

	@Query("select g from ProductTo g WHERE g.prod=(case when :prodId is null then prodId else :prodId end) and g.type=(case when :type is null then type else :type end) and " 
	+ "g.price=(case when :price is null then price else :price end)")
	List<ProductTo> productListByCriteria(@Param("prodId") int prodId , @Param("type") String type);
	
	
	
}
