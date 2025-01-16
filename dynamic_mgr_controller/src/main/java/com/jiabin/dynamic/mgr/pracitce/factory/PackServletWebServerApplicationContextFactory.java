package com.jiabin.dynamic.mgr.pracitce.factory;

import org.springframework.aot.AotDetector;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class PackServletWebServerApplicationContextFactory implements ApplicationContextFactory {

	@Override
	public Class<? extends ConfigurableEnvironment> getEnvironmentType(WebApplicationType webApplicationType) {
		return (webApplicationType != WebApplicationType.SERVLET) ? null : PackApplicationServletEnvironment.class;
	}

	@Override
	public ConfigurableEnvironment createEnvironment(WebApplicationType webApplicationType) {
		return (webApplicationType != WebApplicationType.SERVLET) ? null : new PackApplicationServletEnvironment();
	}

	@Override
	public ConfigurableApplicationContext create(WebApplicationType webApplicationType) {
		return (webApplicationType != WebApplicationType.SERVLET) ? null : createContext();
	}

	private ConfigurableApplicationContext createContext() {
	  PackBeanFactory beanFactory = new PackBeanFactory() ;
		if (!AotDetector.useGeneratedArtifacts()) {
			return new AnnotationConfigServletWebServerApplicationContext(beanFactory) ;
		}
		return new ServletWebServerApplicationContext(beanFactory) ;
	}

}