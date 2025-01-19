package com.jiabin.value.fresh.practice.propertysource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(name = UpdatePropertySource.NAME, value = "extra.properties", ignoreResourceNotFound = true)
public class PackValuePropertySourceConfig {
}
