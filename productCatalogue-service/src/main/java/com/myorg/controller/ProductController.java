package com.myorg.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.infra.api.elements.Collection;
import com.myorg.infra.api.elements.OptionsResponseEntity;
import com.myorg.infra.messageservice.MessageLocalService;
import com.myorg.infra.service.processor.ServiceProcessor;
import com.myorg.model.impl.Product;
import com.myorg.util.ProductHelper;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private MessageLocalService messageLocalService;

	@Autowired
	@Qualifier("ProductService")
	private ServiceProcessor<Product> productServiceProcessor;

	@Autowired
	private ProductHelper productResourceHelper;

	private Collection collectionObject;

	// 1. Add a product
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Product> postProduct(@Valid @RequestBody Product product, HttpServletResponse response)
			throws Throwable {
		productServiceProcessor.processPost(product);
		product.add(linkTo(methodOn(ProductController.class).postProduct(product, null)).withSelfRel());
		response.setHeader(HttpHeaders.ALLOW, "POST,OPTIONS");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,OPTIONS");
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	// 2. Retrieve the list of products based on simple search criteria e.g.
	// product type
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Collection> getProductList(@RequestParam(value = "prodId", required = true) int prodId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "price", required = false) BigDecimal price, HttpServletRequest request,
			HttpServletResponse response) {

		collectionObject = new Collection();

		Product product = new Product(0, name, type, price);
		List<Product> results = productServiceProcessor.processSearchList(product);
		if (results == null) {
			return new ResponseEntity<Collection>(collectionObject, HttpStatus.NOT_FOUND);
		}

		collectionObject.setItems(productResourceHelper.getCollectionResponse(request.getRequestURL().toString(),
				"Product resource", "Product", results, Product.class));
		response.setHeader(HttpHeaders.ALLOW, "GET,OPTIONS");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,OPTIONS");
		return new ResponseEntity<Collection>(collectionObject, HttpStatus.OK);
	}

	// 3. Remove a product from the catalogue
	@RequestMapping(value = "/{ID}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProduct(@PathVariable String ID, HttpServletResponse response) {
		Product product = new Product();
		product.setupModelFromId(ID);
		productServiceProcessor.checkIfEntityExists(product);
		productServiceProcessor.processDelete(product);
		product.add(linkTo(methodOn(ProductController.class).deleteProduct(ID, null)).withSelfRel());
		response.setHeader(HttpHeaders.ALLOW, "DELETE,OPTIONS");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "DELETE,OPTIONS");
		response.setHeader(HttpHeaders.AUTHORIZATION, messageLocalService.getMessage("product.delete"));
		return new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
	}

	// OPTIONS to be treated as a common to know the options available on the
	// particular operation
	@RequestMapping(method = RequestMethod.OPTIONS, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<OptionsResponseEntity> ProductOptions(@RequestParam(value = "prodId", required = true) int prodId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "price", required = false) BigDecimal price, HttpServletRequest request,
			HttpServletResponse responseObj) {

		OptionsResponseEntity response = null;
		response = productResourceHelper.getResourceResponse(request, Product.class);
		responseObj.setHeader(HttpHeaders.ALLOW, "OPTIONS");
		responseObj.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS");
		return new ResponseEntity<OptionsResponseEntity>(response, HttpStatus.OK);

	}

	// To set http header
	@ModelAttribute
	public void setResponseHeader(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader(HttpHeaders.LOCATION, request.getRequestURL().toString());
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "True");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Location,Link,ETag");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
				"Origin, X-Requested-With, Content-Type, Accept,username,x-ibm-client-id,x-ibm-client-secret,iPlanetDirectoryPro");
		response.setHeader(HttpHeaders.CONTENT_LANGUAGE, "en");
		response.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, sdch");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, no-store, no-cache, must-revalidate");
		response.setHeader(HttpHeaders.CONNECTION, "Keep-Alive");
		response.setHeader(HttpHeaders.VARY, "Accept-Encoding");
	}

}
