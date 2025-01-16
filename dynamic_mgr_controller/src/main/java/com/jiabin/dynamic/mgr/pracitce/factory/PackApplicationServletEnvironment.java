package com.jiabin.dynamic.mgr.pracitce.factory;

import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.StandardServletEnvironment;

class PackApplicationServletEnvironment extends StandardServletEnvironment {

	@Override
	protected String doGetActiveProfilesProperty() {
		return null;
	}

	@Override
	protected String doGetDefaultProfilesProperty() {
		return null;
	}

	@Override
	protected ConfigurablePropertyResolver createPropertyResolver(MutablePropertySources propertySources) {
		return ConfigurationPropertySources.createPropertyResolver(propertySources);
	}

}