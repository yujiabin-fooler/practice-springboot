package com.jiabin.extension.point.practice.importselector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class PackDeferredImportSelector implements DeferredImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[] {PluginA.class.getName()} ;
  }

}
