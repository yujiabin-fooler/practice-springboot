package com.jiabin.extension.point.practice.importselector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PackDeferredImportSelector.class, PackImportSelector.class})
public class ImportConfig {

}
