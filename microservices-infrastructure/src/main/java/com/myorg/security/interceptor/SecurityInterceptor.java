package com.myorg.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myorg.infra.api.elements.ExecContext;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private ExecContext execContext;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		 if (request.getMethod().equalsIgnoreCase(RequestMethod.PATCH.name())) {
	            execContext.setPatchRequest(true);
	            execContext.getUserId();
	        }
		 return true;
	}
}
