package com.luv2code.springbootlibrary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luv2code.springbootlibrary.entity.Book;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private String theAllowedOrigin = "http://localhost:3000";
	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.POST, 
												HttpMethod.PATCH, 
												HttpMethod.PUT, 
												HttpMethod.DELETE};
		
		config.exposeIdsFor(Book.class);
		
		disableHttpMethods(Book.class, config, theUnsupportedActions);
		
		/* Configure CORS Mapping */
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigin);
	}

	private void disableHttpMethods(Class<Book> theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		
		config.getExposureConfiguration()
				.forDomainType(theClass)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}
}
