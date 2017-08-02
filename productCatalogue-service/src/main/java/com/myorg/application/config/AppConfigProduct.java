package com.myorg.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.myorg.infra.data.repo.GenericDaoJpaImpl;
import com.myorg.infra.messagelocalserviceImpl.MessageLocalServiceImpl;
import com.myorg.security.interceptor.SecurityInterceptor;
import com.myorg.service.mapping.processor.ProductMappingProcessor;
import com.myorg.service.processor.ProductServiceProcessor;
import com.myorg.util.ProductHelper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSpringDataWebSupport
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@ComponentScan(basePackages = { "com.myorg.data.config", "com.myorg.controller",
		"com.myorg.infra.messageservice", "com.myorg.infra.exception",
		"com.myorg.infra.api.elements","com.myorg.security.interceptor"})


public class AppConfigProduct extends WebMvcConfigurerAdapter {

	@Autowired
	SecurityInterceptor securityInterceptor;

	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public GenericDaoJpaImpl gerericeDaoImpl() {
		return new GenericDaoJpaImpl();
	}

	@Bean(name = "ProductService")
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ProductServiceProcessor productServiceProcessor() {
		return new ProductServiceProcessor();
	}

	@Bean(name = "ProductMapping")
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ProductMappingProcessor productMappingProcessor() {
		return new ProductMappingProcessor();
	}

	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ProductHelper getCollectionproductResourceImpl() {
		return new ProductHelper();
	}

		@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public MessageLocalServiceImpl messageLocalServiceImpl() {
		return new MessageLocalServiceImpl();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor);
	}

}
