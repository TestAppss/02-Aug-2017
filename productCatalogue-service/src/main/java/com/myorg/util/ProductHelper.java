package com.myorg.util;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myorg.infra.api.elements.Options;
import com.myorg.infra.api.elements.OptionsLink;
import com.myorg.infra.api.elements.OptionsResponseEntity;
import com.myorg.infra.api.elements.Parameters;
import com.myorg.infra.util.ResourceHelper;
import com.myorg.infra.util.StringConstants;
import com.myorg.model.impl.Product;

public class ProductHelper extends ResourceHelper<Product> {

	private final static String PRODUCT_OPTION_TITLE = "Options for basic operations on Product Resource";
	private final static String PRODUCT_ID_OPTION_TITLE = "Options on a particular ID resource";
	private final static String PRODUCT_SEARCH_TITLE = "Searches the Product";
	private final static String PRODUCT_CREATE_TITLE = "Creates a new Product";

	@Override
	protected void generateResourceParameters(OptionsLink optionLink) {
		// TODO Auto-generated method stub

	}

	public OptionsResponseEntity getResourceResponse(HttpServletRequest request,
			Class<? extends ResourceSupport> className) {
		OptionsResponseEntity optionsResponseEntity = new OptionsResponseEntity();

		Options _options = new Options();
		String href = request.getRequestURL().toString();

		optionsResponseEntity.setTitle(PRODUCT_OPTION_TITLE);

		_options.getLinks()
				.add(addCollectionOptionLinkGet(href, PRODUCT_SEARCH_TITLE, StringConstants.REL_SEARCH, className));
		_options.getLinks()
				.add(addCollectionOptionLinkPost(href, PRODUCT_CREATE_TITLE, StringConstants.REL_CREATE, className));

		optionsResponseEntity.setOptions(_options);
	
		return optionsResponseEntity;
	}

	public OptionsResponseEntity getResourceResponseID(String ID, HttpServletRequest request,
			Class<? extends ResourceSupport> className) {
		OptionsResponseEntity optionsResponseEntity = new OptionsResponseEntity();

		Options _options = new Options();
		String href = request.getRequestURL().toString();

		optionsResponseEntity.setTitle(PRODUCT_ID_OPTION_TITLE);
		_options.getLinks().add(addCollectionOptionLinkID(href, RequestMethod.GET.name(),
				"Returns the data for particular ID", StringConstants.REL_GET, className));

		optionsResponseEntity.setOptions(_options);
	
		return optionsResponseEntity;
	}

	protected OptionsLink addCollectionOptionLinkID(String href, String methodName, String title, String rel,
			Class<? extends ResourceSupport> className) {

		OptionsLink optionLink = new OptionsLink();

		optionLink.setHref(href);
		optionLink.setMethod(methodName);
		optionLink.setTitle(title);
		optionLink.setRel(rel);
		generateSchema(optionLink, className);
		generateResourceParametersID(optionLink);
		return optionLink;
	}

	protected void generateResourceParametersID(OptionsLink optionLink) {
		List<Parameters> parameters = new ArrayList<>();
		parameters.add(new Parameters("query", "ID", "string", true, "Product ID"));
		optionLink.setParameters(parameters);
	}

}
