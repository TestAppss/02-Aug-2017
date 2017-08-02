package com.myorg.infra.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.myorg.infra.api.elements.Item;
import com.myorg.infra.api.elements.OptionsLink;
import com.myorg.infra.components.Model;

public abstract class ResourceHelper<ModelImpl extends Model> {

	protected abstract void generateResourceParameters(OptionsLink optionLink);

	public ArrayList<Item> getCollectionResponse(String href, String name, String title, List<ModelImpl> results,
			Class<? extends ResourceSupport> className) {
		Item item = null;
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i = 0; i < results.size(); i++) {
			item = new Item();
			item.setHref(href + "/" + results.get(i).generateIdFromModel());
			item.setName(name);
			item.setTitle(title);
			item.setSummary(results.get(i));
			items.add(item);
		}
		return items;

	}

	protected OptionsLink addCollectionOptionLinkGet(String href, String title, String rel,
			Class<? extends ResourceSupport> className) {
		return addCollectionOptionLink(href, RequestMethod.GET.name(), title, rel, className);
	}

	protected OptionsLink addCollectionOptionLinkPost(String href, String title, String rel,
			Class<? extends ResourceSupport> className) {
		return addCollectionOptionLink(href, RequestMethod.POST.name(), title, rel, className);
	}

	protected OptionsLink addCollectionOptionLinkPatch(String href, String title, String rel,
			Class<? extends ResourceSupport> className) {
		return addCollectionOptionLink(href, RequestMethod.PATCH.name(), title, rel, className);
	}

	protected OptionsLink addCollectionOptionLinkDelete(String href, String title, String rel,
			Class<? extends ResourceSupport> className) {
		return addCollectionOptionLink(href, RequestMethod.DELETE.name(), title, rel, className);
	}

	protected OptionsLink addCollectionOptionLink(String href, String methodName, String title, String rel,
			Class<? extends ResourceSupport> className) {
		OptionsLink optionLink = new OptionsLink();
		optionLink.setHref(href);
		optionLink.setMethod(methodName);
		optionLink.setTitle(title);
		optionLink.setRel(rel);
		generateSchema(optionLink, className);
		generateResourceParameters(optionLink);
		return optionLink;
	}

	protected void generateSchema(OptionsLink customSchema, Class<? extends ResourceSupport> className) {
		customSchema.setSchema(generateJsonSchema(className));
	}

	protected JsonSchema generateJsonSchema(Class<? extends ResourceSupport> className) {
		JsonSchema schema = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();

			mapper.acceptJsonFormatVisitor(className, visitor);
			schema = visitor.finalSchema();
		} catch (JsonProcessingException e) {
			System.out.println(e);
		}
		return schema;
	}
}
