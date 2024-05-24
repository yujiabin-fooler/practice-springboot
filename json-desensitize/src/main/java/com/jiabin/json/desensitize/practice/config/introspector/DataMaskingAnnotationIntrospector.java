package com.jiabin.json.desensitize.practice.config.introspector;

import com.jiabin.json.desensitize.practice.config.DataMaskingSerializer;
import com.jiabin.json.desensitize.practice.config.annotation.DataMasking;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva   2022-09-17 23:29
 */
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

