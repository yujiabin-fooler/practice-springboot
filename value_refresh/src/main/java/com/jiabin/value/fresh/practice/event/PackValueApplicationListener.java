package com.jiabin.value.fresh.practice.event;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.jiabin.value.fresh.practice.bean.PackValueMetadata;
import com.jiabin.value.fresh.practice.bean.Pair;
import com.jiabin.value.fresh.practice.config.PackValueBeanPostProcessor;

@Component
public class PackValueApplicationListener implements ApplicationListener<PackValueChangeEvent> {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  @Override
  public void onApplicationEvent(PackValueChangeEvent event) {
    Pair pair = (Pair) event.getSource() ;
    Collection<PackValueMetadata> metadatas = PackValueBeanPostProcessor.VALUES.get(pair.getKey()) ;
    if (metadatas != null) {
      metadatas.forEach(metadata -> {
        metadata.invokeValue(pair.getValue()) ;
      });
      logger.info("更新配置: {}, 完成", metadatas) ;
    }
  }
}
