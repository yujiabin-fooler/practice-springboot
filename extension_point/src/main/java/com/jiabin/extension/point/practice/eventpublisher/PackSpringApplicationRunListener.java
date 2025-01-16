package com.jiabin.extension.point.practice.eventpublisher;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

public class PackSpringApplicationRunListener implements SpringApplicationRunListener, Ordered {

  private SpringApplication app ;
  private final String[] args ;
  
  public PackSpringApplicationRunListener(SpringApplication app, String[] args) {
    this.app = app ;
    this.args = args ;
    System.err.printf("%s%s%n", app, Arrays.toString(this.args)) ;
  }
  
  @Override
  public int getOrder() {
    return 1 ;
  }

  // 在 ApplicationContext 创建并准备好之后，但在加载源之前被调用。
  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    System.err.println("ApplicationContext 创建并准备好, 还没有开始调用refresh方法") ;
  }

  // 上下文已经被刷新，并且应用程序已经启动，但是 CommandLineRunners 和 ApplicationRunners 尚未被调用。
  @Override
  public void started(ConfigurableApplicationContext context, Duration timeTaken) {
    System.err.printf("上下文已经被刷新，并且应用程序已经启动，但 *Runners 尚未被调用, 应用程序启动所花费时间: %dms%n", timeTaken.toMillisPart()) ;
  }
  
}
