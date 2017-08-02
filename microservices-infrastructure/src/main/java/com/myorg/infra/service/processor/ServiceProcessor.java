package com.myorg.infra.service.processor;

import java.util.List;

public interface ServiceProcessor<ModelImpl> {

	void processPost(ModelImpl model) throws Throwable;

	List<ModelImpl> processSearchList(ModelImpl model);

	ModelImpl processGetById(ModelImpl model);

	void processUpdate(ModelImpl model) throws Throwable;

	void processDelete(ModelImpl model);

	boolean checkIfEntityExists(ModelImpl model);
	
}
