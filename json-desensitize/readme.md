### 前言

`2022.09.17`

> 公众号上看到的JSON数据加密的博客，觉得还挺实用的，奇怪以前怎么就没想到

网上找了下大概有两种思路：

- AOP 环绕切面，通过注解标记需要处理的字段，然后在切面中反射后，再处理脱敏
- 类似 `@JsonFormat` 注解，使用自定义注解跟字段解析器

Demo代码：[json-desensitize · 路的尽头在哪/code of shiva - 码云 - 开源中国 (gitee.com)](https://gitee.com/qianwei4712/code-of-shiva/tree/master/json-desensitize)

<br/>

### AOP 切面方式

![](https://shiva.oss-cn-hangzhou.aliyuncs.com/picture-master/202204/image-20220917203406361.png)

**缺点：**

- 因为要经过反射，性能消耗肯定要比较高
- 实现代码有点绕，demo 写到一半就不想写了。。

> **所以这个例子其实不能用。。。controller返回类型不确定，需要兼容各种类型进行反射判断，实在是有点复杂**
>
> **这里是按最简单的方式做下demo**

先加注解：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReadableSensitiveVerify {
    /**
     * mobile、name、idCard；
     * 有需要可以改为枚举
     */
    public String type() default "";
}

@Data
@Builder
public class User implements Serializable {
    @ReadableSensitiveVerify(type = "name")
    private String name;
    @ReadableSensitiveVerify(type = "idCard")
    private String idCard;
    @ReadableSensitiveVerify(type = "mobile")
    private String mobile;
}
```

脱敏方法，随便写的，仅供测试：

```java
public class CommonUtil {
    /**
     * 身份证号脱敏
     */
    public static String idCardDesensitized(String idCard) {
        //非正确号码，随意了
        if (idCard == null || idCard.length() < 18) {
            return idCard;
        }
        return idCard.substring(0, 4) + "******" + idCard.substring(idCard.length() - 6);
    }
    /**
     * 姓名脱敏
     */
    public static String nameDesensitized(String name) {
        if (name == null || "".equals(name)) {
            return name;
        }
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }
        if (name.length() == 3) {
            return name.charAt(0) + "*" + name.charAt(2);
        }
        if (name.length() == 4) {
            return name.charAt(0) + "**" + name.charAt(3);
        }
        return name;
    }
    /**
     * 手机号脱敏
     */
    public static String mobileDesensitized(String mobile) {
        if (mobile == null || "".equals(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return mobile.substring(0, 3) + "******" + mobile.substring(mobile.length() - 4);
    }
}
```



接口代码：

```java
@GetMapping("getUser")
public User getUser() {
    return User.builder().name("毛教员").idCard("330224193212541215").mobile("15321548565").build();
}
```

AOP 注解：

```java
@Slf4j
@Aspect
@Component
public class DesensitizedAspect {

    @Around("execution(* cn.shiva.controller.UserController.listUser(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("环绕通知执行，脱敏开始了");
        // 1.执行方法，先拿到返回值
        Object obj = proceedingJoinPoint.proceed();
        // 2.没有返回值，或者是string类型，那就不搞了
        // 注意：这里也可能已经是json了，所以需要写代码的时候小心点
        if (obj == null || isPrimitive(obj.getClass())) {
            return obj;
        }
        // 3.开始处理数据
        //再次注意，一般我们的返回数据都是 code、msg、data三个字段的。所以注意返回值结构
        dealData(obj);
        return obj;
    }

    /**
     * 基本数据类型和String类型判断
     */
    private boolean isPrimitive(Class<?> clz) {
        try {
            if (String.class.isAssignableFrom(clz) || clz.isPrimitive()) {
                return true;
            } else {
                return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 实际数据处理
     */
    private void dealData(Object obj) {
        if (null == obj) {
            return;
        }
        //一般返回的都是通过泛型，或者obj，所以实际是什么方法要自己手写判断
        Field[] fields = User.class.getDeclaredFields();
        replace(fields, obj);
    }

    /**
     * 脱敏敏感字段
     */
    private void replace(Field[] fields, Object o) {
        try {
            for (Field f : fields) {
                if (f == null) {
                    continue;
                }
                //设置private字段可访问
                f.setAccessible(true);

                // 分析过程：
                // 1.一般返回也就三种，嵌套对象、简单对象、
                // 2.TODO 如果是对象类型的，循环字段检查下有没有嵌套对象，这部分例子里就不写了
                // 3.集合对象，也要特殊处理，递归方法继续调用
                Class<?> curFieldType = f.getType();
                if (curFieldType.equals(List.class)) {
                    List<?> record = (List<?>) f.get(o);
                    if (record != null && !record.isEmpty()) {
                        for (Object obj : record) {
                            Field[] ff = obj.getClass().getDeclaredFields();
                            replace(ff, obj);
                        }
                    }
                }
                //处理普通字符串字段
                ReadableSensitiveVerify annotation = f.getAnnotation(ReadableSensitiveVerify.class);
                if (annotation != null) {
                    f.getType();
                    String valueStr = (String) f.get(o);
                    if (valueStr != null && !"".equals(valueStr)) {
                        String type = annotation.type();
                        if ("name".equals(type)) {
                            f.set(o, CommonUtil.nameDesensitized(valueStr));
                        }
                        if ("mobile".equals(type)) {
                            f.set(o, CommonUtil.mobileDesensitized(valueStr));
                        }
                        if ("idCard".equals(type)) {
                            f.set(o, CommonUtil.idCardDesensitized(valueStr));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

执行返回结果：

![](https://shiva.oss-cn-hangzhou.aliyuncs.com/picture-master/202204/image-20220917220003375.png)





<br/>

### 自定义注解方式

下面代码基本一行没改，大佬的demo可以直接用：[通过注解实现接口返回数据脱敏实现 - 掘金 (juejin.cn)](https://juejin.cn/post/7110110794188062727)

可以自行扩展，这个方式比AOP要简单容易读得多。

**自定义数据注解，并可以配置数据脱敏策略**

```java
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {
    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;
}
```

**自定义Serializer，参考jackson的StringSerializer，下面的示例只针对String类型进行脱敏**

```java
public interface DataMaskingOperation {
    String MASK_CHAR = "*";   
    String mask(String content, String maskChar);
}
```

```java
public enum DataMaskingFunc {
    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    NAME((str, maskChar) -> {
        return CommonUtil.nameDesensitized(str);
    }),
    IDCARD((str, maskChar) -> {
        return CommonUtil.idCardDesensitized(str);
    }),
    MOBILE((str, maskChar) -> {
        return CommonUtil.mobileDesensitized(str);
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingOperation operation;
    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }
    public DataMaskingOperation operation() {
        return this.operation;
    }

}
```

```java
public final class DataMaskingSerializer extends StdScalarSerializer<Object> {
    private final DataMaskingOperation operation;

    public DataMaskingSerializer() {
        super(String.class, false);
        this.operation = null;
    }

    public DataMaskingSerializer(DataMaskingOperation operation) {
        super(String.class, false);
        this.operation = operation;
    }


    @Override
    public boolean isEmpty(SerializerProvider prov, Object value) {
        String str = (String) value;
        return str.isEmpty();
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.isNull(operation)) {
            String content = DataMaskingFunc.ALL_MASK.operation().mask((String) value, null);
            gen.writeString(content);
        } else {
            String content = operation.mask((String) value, null);
            gen.writeString(content);
        }
    }

    @Override
    public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("string", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this.visitStringFormat(visitor, typeHint);
    }
}
```

**自定义AnnotationIntrospector，适配我们自定义注解返回相应的Serializer**

```java
@Slf4j
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {
    @Override
    public Object findSerializer(Annotated am) {
        DataMasking annotation = am.getAnnotation(DataMasking.class);
        if (annotation != null) {
            return new DataMaskingSerializer(annotation.maskFunc().operation());
        }
        return null;
    }
}
```

**覆盖ObjectMapper**

```java
@Configuration(
        proxyBeanMethods = false
)
public class DataMaskConfiguration {

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
    static class JacksonObjectMapperConfiguration {
        JacksonObjectMapperConfiguration() {
        }

        @Bean
        @Primary
        ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
            ObjectMapper objectMapper = builder.createXmlMapper(false).build();
            AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
            AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new DataMaskingAnnotationIntrospector());
            objectMapper.setAnnotationIntrospector(newAi);
            return objectMapper;
        }
    }
}
```

**返回对象加上注解、接口返回**

```java
@Data
@Builder
public class User implements Serializable {
    @DataMasking(maskFunc = DataMaskingFunc.NAME)
    private String name;
    @DataMasking(maskFunc = DataMaskingFunc.IDCARD)
    private String idCard;
    @DataMasking(maskFunc = DataMaskingFunc.MOBILE)
    private String mobile;
}
```

```java
    @GetMapping("listUserJson")
    public List<User> listUserJson() {
        return get();
    }
    private List<User> get() {
        //模拟下名字分别是2、3、4个字
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().name("毛教员").idCard("330224193212541215").mobile("15321548565").build());
        userList.add(User.builder().name("邓太公").idCard("330265189515485621").mobile("15447589658").build());
        userList.add(User.builder().name("习大").idCard("330279152154885625").mobile("12552365448").build());
        return userList;
    }
```

测试结果：

![](https://shiva.oss-cn-hangzhou.aliyuncs.com/picture-master/202204/image-20220918001552382.png)



<br/>

### 参考文章

- [通过注解实现接口返回数据脱敏实现 - 掘金 (juejin.cn)](https://juejin.cn/post/7110110794188062727)
- [datamask-demo: 自定义注解，jaskson返回信息脱敏demo (gitee.com)](https://gitee.com/lvzxn/datamask-demo)
- [【IT老齐140】非常实用！Spring AOP与自定义注解实现共性需求_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1VS4y127Lo/?spm_id_from=333.788.recommend_more_video.1&vd_source=e768d8ae5d35e9620400ecb1e8983682)
- [AOP实现注解式脱敏数据明文查询_回炉重造P的博客-CSDN博客](https://blog.csdn.net/qq_41733192/article/details/126473321)
- [springboot 使用aop实现数据脱敏_浮躁的风的博客-CSDN博客_springboot 数据脱敏](https://blog.csdn.net/Ming13416908424/article/details/125441637)























