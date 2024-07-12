




什么是Swagger？
Swagger目前是比较主流的RESTful风格的API文档工具，做过开发的人应该都用过它吧！

图片
它提供了一套工具和规范，让开发人员能够更轻松地创建和维护可读性强、易于使用和交互的API文档（官方口吻）。

title: Swagger
desc: Swagger 官方网站
logo: https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg
link: https://swagger.io/
为什么用Swagger？
以往在没有这样的API文档工具，开发人员需要手动编写和维护功能API的文档。而且，由于API变更往往难以及时更新到文档中，这可能会给依赖文档的开发者带来困惑。

说几个Swagger的特点：

最重要的一点可以根据代码注解自动生成API文档，能生成的绝对不手写，而且API文档与API定义会同步更新。

它提供了一个可执行的Web界面，支持API在线测试，可以直接在界面上直接设置参数测试，不用额外的测试工具或插件。

支持多种编程语言，Java、PHP、Python等语言都支持，喜欢什么语言构建API都行。

总的来说，Swagger可以让我们更多时间在专注于编写代码（摸鱼），而不是花费额外精力来维护文档，实践出真知先跑个demo试试。

Swagger搭建
maven 依赖
目前使用的版本是Swagger3.0、Springboot 2.7.6，Swagger2.0与3.0依赖包名称的变化有些大，需要特别注意一下。

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.7.6</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>

配置类
首先我们创建一个控制器TestController类，里边只有一个最简单的请求 /test。

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(String name) {
        return name;
    }
}
接下来创建配置类SwaggerConfig，类标注@EnableSwagger2注解是关键，到这最简单的Swagger文档环境就搭建好了。

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

}
启动报错
启动时可能会报如下的错误，这是由于高版本的Springboot与Swagger版本使用的路径匹配策略冲突导致的。

Springfox使用的路径匹配规则为AntPathMatcher 的，而SpringBoot2.7.6使用的是PathPatternMatcher，两者冲突了。

org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:181) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:54) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:356) ~[spring-context-5.3.24.jar:5.3.24]
at java.lang.Iterable.forEach(Iterable.java:75) ~[na:1.8.0_341]
at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:155) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:123) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:935) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:586) ~[spring-context-5.3.24.jar:5.3.24]
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:147) ~[spring-boot-2.7.6.jar:2.7.6]
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:731) [spring-boot-2.7.6.jar:2.7.6]
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:408) [spring-boot-2.7.6.jar:2.7.6]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:307) [spring-boot-2.7.6.jar:2.7.6]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1303) [spring-boot-2.7.6.jar:2.7.6]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1292) [spring-boot-2.7.6.jar:2.7.6]
at com.jiabin.webhook.practice.SwaggerApplication.main(SwaggerApplication.java:10) [classes/:na]
解决方案
这个错误的解决办法比较多，我整理了四种解决此问题的方案，你看哪个更合适你。

1、降低版本
SpringBoot版本降低到2.5.X 、springfox降到3.X 以下可以解决问题，不过不推荐这么做，毕竟降配置做兼容显得有点傻。

2、统一路径匹配策略
将SpringMVC的匹配URL路径的策略改为ant_path_matcher，application.yml文件增加如下的配置：

spring:
mvc:
pathmatch:
matching-strategy: ant_path_matcher
3、@EnableWebMvc注解
在配置类SwaggerConfig上标注@EnableWebMvc注解也可以解决。

Swagger框架需要通过解析和扫描带有注解的Controller类和方法来生成API文档。@EnableWebMvc注解会注册一个RequestMappingHandlerMapping的Bean，并将其作为默认的请求映射处理器，以确保这些Controller类和方法能够被正确处理，可以与Swagger的路径配置规则相匹配，从而使得Swagger能够成功生成API文档。

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {

}
4、注册 BeanPostProcessor
也可以自行实现兼容逻辑来解决这个问题，我们可以在Spring容器中注册一个BeanPostProcessor，在该处理器中对 HandlerMappings 进行定制。

通过过滤掉已存在PatternParser的映射，意味着我们可以将Swagger特定的HandlerMappings添加到HandlerMappings列表中，从而使用自定义的设置来替代原有的HandlerMappings。

这样修复了可能导致Swagger无法正常使用的问题。

@Bean
public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
return new BeanPostProcessor() {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
            }
            return bean;
        }

        private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
            List<T> copy = mappings.stream()
                    .filter(mapping -> mapping.getPatternParser() == null)
                    .collect(Collectors.toList());
            mappings.clear();
            mappings.addAll(copy);
        }

        @SuppressWarnings("unchecked")
        private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
            try {
                Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                field.setAccessible(true);
                return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.warn("修改WebMvcRequestHandlerProvider的属性：handlerMappings出错，可能导致swagger不可用", e);
                throw new IllegalStateException(e);
            }
        }
    };
}
访问 swagger-ui
到这，问题解决！我们访问Swagger文档路径 http://127.0.0.1:9002/swagger-ui/index.html ，能够看到我们写的 API 信息以及一些Swagger 文档的默认配置信息。

图片
注意到我们只写了一个 /test接口，但这里确把这个方法的所有请求方式都列了出来，因为我们在 controller 方法中使用了@RequestMapping注解，并没有具体的指定接口的请求方式，所以避免文档冗余，尽量指定请求方式或者使用指定请求方式的 @XXXMapping 注解。

指定请求方式后：

图片
API文档配置
上边我们访问的文档中展示的数据都是默认的配置，现在咱们来定制化一下文档。

Springfox提供了一个Docket对象，供我们灵活的配置Swagger的各项属性。Docket对象内提供了很多的方法来配置文档，下边介绍下常用的配置项。

select
select()返回一个ApiSelectorBuilder对象，是使用apis()、paths()两个方法的前提，用于指定Swagger要扫描的接口和路径。

apis
默认情况下，Swagger会扫描整个项目中的接口，通过 apis()方法，你可以传入一个RequestHandlerSelector对象实例来指定要包含的接口所在的包路径。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.select()
.apis(RequestHandlerSelectors.basePackage("com.jiabin.webhook.practice.controller"))
.build();
}
paths
仅将某些特定请求路径的API展示在Swagger文档中，例如路径中包含/test。可以使用 apis() 和 paths()方法一起来过滤接口。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.select()
.paths(PathSelectors.ant("/test/**"))
.build();
}
groupName
为生成的Swagger文档指定分组的名称，用来区分不同的文档组。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.groupName("用户分组")
.build();
}
图片
实现文档的多个分组，则需创建多个 Docket 实例，设置不同的组名，和组内过滤 API 的条件。

@Bean
public Docket docket1(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.groupName("商家分组")
.select()
.paths(PathSelectors.ant("/test1/**"))
.build();
}
图片
20230824101153
apiInfo
设置API文档的基本信息，例如标题、描述、版本等。你可以使用ApiInfo对象自定义信息。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.apiInfo(apiInfo()); // 文档基础配置
}

private ApiInfo apiInfo() {
Contact contact = new Contact("测试", "http://fire100.top", "email@example.com");
return new ApiInfoBuilder()
.title("Swagger学习")
.description("程序员测试-带你一起学习 Swagger")
.version("v1.0.1")
.termsOfServiceUrl("http://fire100.top")
.contact(contact)
.license("许可证")
.licenseUrl("许可链接")
.extensions(Arrays.asList(
new StringVendorExtension("我是", "小富"),
new StringVendorExtension("你是", "谁")
))
.build();
}
对应的Swagger文档页面上展示的位置

图片
enable
启用或禁用Swagger文档的生成，有时测试环境会开放API文档，但在生产环境则要禁用，可以根据环境变量控制是否显示。

@Bean
public Docket docket(Environment environment) {
// 可显示 swagger 文档的环境
Profiles of = Profiles.of("dev", "test","pre");
boolean enable = environment.acceptsProfiles(of);

    return new Docket(DocumentationType.SWAGGER_2)
            .enable(enable)
            .apiInfo(apiInfo()); // 文档基础配置
}
host
API文档显示的主机名称或IP地址，即在测试执行接口时使用的IP或域名。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.host("http://test.com") // 请求地址
.apiInfo(apiInfo()); // 文档基础配置
}
securitySchemes
配置API安全认证方式，比如常见的在header中设置如Bearer、Authorization、Basic等鉴权字段，ApiKey对象中字段含义分别是别名、鉴权字段key、鉴权字段添加的位置。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.securitySchemes(
Arrays.asList(
new ApiKey("Bearer鉴权", "Bearer", "header"),
new ApiKey("Authorization鉴权", "Authorization", "header"),
new ApiKey("Basic鉴权", "Basic", "header")
)
);
}
这样配置后，Swagger文档将会包含一个Authorize按钮，供用户输入我们设定的Bearer 、Authorization、Basic进行身份验证。

图片
securityContexts
securitySchemes方法中虽然设置了鉴权字段，但此时在测试接口的时候不会自动在 header中加上鉴权字段和值，还要配置API的安全上下文，指定哪些接口需要进行安全认证。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.securitySchemes(
Arrays.asList(
new ApiKey("Bearer鉴权", "Bearer", "header"),
new ApiKey("Authorization鉴权", "Authorization", "header"),
new ApiKey("Basic鉴权", "Basic", "header")
)
)
.securityContexts(Collections.singletonList(securityContext()));
}

private SecurityContext securityContext() {
return SecurityContext.builder()
.securityReferences(
Arrays.asList(
new SecurityReference("Authorization", new AuthorizationScope[0]),
new SecurityReference("Bearer", new AuthorizationScope[0]),
new SecurityReference("Basic", new AuthorizationScope[0])))
.build();
}
现在在测试调用API接口时，header中可以正常加上鉴权字段和值了。

图片
tags
为API文档中的接口添加标签，标签可以用来对API进行分类或分组，并提供更好的组织和导航功能。

@Bean
public Docket docket(Environment environment) {
return new Docket(DocumentationType.SWAGGER_2)
.tags(new Tag("测试接口", "测试相关的测试接口"))
}
授权登录
出于对系统安全性的考虑，通常我们还会为API文档增加登录功能。

引入maven依赖
swagger的安全登录是基于security实现的，引入相关的maven依赖。

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
登录配置
application.yml文件中配置登录swagger的用户名和密码。

spring:
security:
user:
name: admin
password: 123456
再次访问文档就会出现如下的登录页

图片
文档注解
当我们希望在Swagger文档中提供详细和完整的内容时，还可以使用其他许多Swagger内置注解来进一步丰富和定制API文档。

@ApiIgnore
上边我们提到可以根据指定路径或者包路径来提供API，也可以使用粒度更细的@ApiIgnore注解，来实现某个API在文档中忽略。

@ApiIgnore
@GetMapping("/user2/{id}")
public User test2(@PathVariable Integer id, @RequestBody User user) {
return user;
}
@ApiModel
在我们的接口中，只要使用实体作为参数或响应体，Swagger就会自动扫描到它们，但你会发现目前这些实体缺乏详细的描述信息。为了让使用者通俗易懂，需要使用swagger提供的注解为这些实体添加详细的描述。

图片
@ApiModel注解的使用在实体类上，提供对Swagger Model额外信息的描述。

图片
@ApiModelProperty
@ApiModelProperty 注解为实体类中的属性添加描述，提供了字段名称、是否必填、字段示例等描述信息。

@ApiModel(value = "用户实体类", description = "用于存放用户登录信息")
@Data
public class User {

    @ApiModelProperty(value = "用户名字段", required = true, example = "测试")
    private String name;

    @ApiModelProperty(value = "年龄", required = true, example = "19")
    private Integer age;

    @ApiModelProperty(value = "邮箱", required = true, example = "测试")
    private String email;
}
@Api
@Api 注解用于标记一个控制器（controller）类，并提供接口的详细信息和配置项。

value：API 接口的描述信息，由于版本swagger版本原因，value可能会不生效可以使用description
hidden：该 API 是否在 Swagger 文档中隐藏
tags：API 的标签，如果此处与 new Docket().tags 中设置的标签一致，则会将该 API 放入到这个标签组内
authorizations：鉴权配置，配合 @AuthorizationScope 注解控制权限范围或者特定密钥才能访问该API。
produces：API的响应内容类型，例如 application/json。
consumes：API的请求内容类型，例如 application/json。
protocols：API支持的通信协议。
@Api(value = "用户管理接口描述",
description = "用户管理接口描述",
hidden = false,
produces = "application/json",
consumes = "application/json",
protocols = "https",
tags = {"用户管理"},
authorizations = {
@Authorization(value = "apiKey", scopes = {
@AuthorizationScope(scope = "read:user", description = "读权限"),
@AuthorizationScope(scope = "write:user", description = "写权限")
}),
@Authorization(value = "basicAuth")
})
@RestController
public class TestController {

}
@ApiOperation
@ApiOperation该注解作用在接口方法上，用来对一个操作或HTTP方法进行描述。

value：对接口方法的简单说明
notes：对操作的详细说明。
httpMethod：请求方式
code：状态码，默认为 200。可以传入符合标准的HTTP Status Code Definitions。
hidden：在文档中隐藏该接口
response：返回的对象
tags：使用该注解后，该接口方法会单独进行分组
produces：API的响应内容类型，例如 application/json。
consumes：API的请求内容类型，例如 application/json。
protocols：API支持的通信协议。
authorizations：鉴权配置，配合 @AuthorizationScope 注解控制权限范围或者特定密钥才能访问该API。
responseHeaders：响应的header内容

@ApiOperation(
value = "获取用户信息",
notes = "通过用户ID获取用户的详细信息",
hidden = false,
response = UserDto.class,
tags = {"用户管理"},
produces = "application/json",
consumes = "application/json",
protocols = "https",
authorizations = {
@Authorization(value = "apiKey", scopes = {@AuthorizationScope(scope = "read:user", description = "读权限")}),
@Authorization(value = "Basic")
},
responseHeaders = {@ResponseHeader(name = "X-Custom-Header", description = "Custom header", response = String.class)},
code = 200,
httpMethod = "GET"
)
@GetMapping("/user1")
public UserDto user1(@RequestBody User user) {
return new UserDto();
}
@ApiImplicitParams
@ApiImplicitParams注解用在方法上，以数组方式存储，配合@ApiImplicitParam 注解使用。

@ApiImplicitParam
@ApiImplicitParam注解对API方法中的单一参数进行注解。

注意这个注解@ApiImplicitParam必须被包含在注解@ApiImplicitParams之内。

name：参数名称
value：参数的简短描述
required：是否为必传参数
dataType：参数类型，可以为类名，也可以为基本类型（String，int、boolean等）
paramType：参数的传入（请求）类型，可选的值有 path、query、body、header、form。
@ApiImplicitParams({
@ApiImplicitParam(name = "用户名", value = "用户名称信息", required = true, dataType = "String", paramType = "query")
})
@GetMapping("/user")
public String user(String name) {
return name;
}
@ApiParam()
@ApiParam()也是对API方法中的单一参数进行注解，其内部属性和@ApiImplicitParam注解相似。

@GetMapping("/user4")
public String user4(@ApiParam(name = "主键ID", value = "@ApiParam注解测试", required = true) String id) {
return id;
}
@ApiResponses
@ApiResponses注解可用于描述请求的状态码，作用在方法上，以数组方式存储，配合 @ApiResponse注解使用。

@ApiResponse
@ApiResponse注解描述一种请求的状态信息。

code：HTTP请求响应码。
message：响应的文本消息
response：返回类型信息。
responseContainer：如果返回类型为容器类型，可以设置相应的值。有效值为 "List"、 "Set"、"Map"其他任何无效的值都会被忽略。
@ApiResponses(value = {
@ApiResponse(code = 200, message = "@ApiResponse注解测试通过", response = String.class),
@ApiResponse(code = 401, message = "可能参数填的有问题", response = String.class),
@ApiResponse(code = 404, message = "可能请求路径写的有问题", response = String.class)
})
@GetMapping("/user4")
public String user4(@ApiParam(name = "主键ID", value = "@ApiParam注解测试", required = true) String id) {
return id;
}
......

总结
尽管在面试中不会过多考察Swagger这类工具，但作为开发者，养成良好的文档规范习惯是非常重要的，无论使用Swagger还是其他文档工具，编写清晰、详尽的API文档都应该是我们的素养之一。

好了，今天的内容分享就到这里了，感谢大家的收看，我们下篇见。

代码示例
https://github.com/chengxy-nds/Springboot-Notebook/tree/master/springboot101/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3/springboot-swagger
- END -
