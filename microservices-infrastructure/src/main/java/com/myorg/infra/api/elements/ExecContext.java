package com.myorg.infra.api.elements;

import static com.myorg.infra.util.StringConstants.SEPERATOR_BLANK;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExecContext {

	private String userId = SEPERATOR_BLANK;

	private boolean isPatchRequest = false;

	private String language;
	


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isPatchRequest() {
		return isPatchRequest;
	}

	public void setPatchRequest(boolean isPatchRequest) {
		this.isPatchRequest = isPatchRequest;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}