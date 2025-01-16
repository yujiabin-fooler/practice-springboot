package com.jiabin.jackson.practice.custom_annotation;

import java.io.IOException;
import java.util.Collections;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

  private Sensitive sensitive;

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    String val = value;
    if (sensitive != null && StringUtils.hasLength(val)) {
      String m = sensitive.mask();
      int start = sensitive.start();
      int end = sensitive.end();
      int totalLength = value.length();

      if (totalLength <= 2) {
        val = totalLength == 1 ? value + m : value.substring(0, 1) + m;
      } else if (totalLength <= 6) {
        val = value.substring(0, 1) + String.join("", Collections.nCopies(totalLength - 2, m))
            + value.substring(totalLength - 1);
      } else {
        int prefixLength = Math.min(start, totalLength - 1);
        int suffixLength = Math.min(end, totalLength - 1);

        if (prefixLength > totalLength) {
          prefixLength = totalLength / 2;
        }
        if (suffixLength > totalLength) {
          suffixLength = totalLength / 2;
        }

        int maskLength = Math.max(0, totalLength - (prefixLength + suffixLength));
        if (maskLength == 0) {
          prefixLength -= 2;
          suffixLength -= 2;
          maskLength = Math.max(2, totalLength - (prefixLength + suffixLength));
        }

        prefixLength = Math.min(prefixLength, totalLength - 1);
        suffixLength = Math.min(suffixLength, totalLength - 1);

        maskLength = totalLength - prefixLength - suffixLength;

        val = value.substring(0, prefixLength) + String.join("", Collections.nCopies(maskLength, m))
            + value.substring(totalLength - suffixLength);
      }
    }
    gen.writeString(val);
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
      throws JsonMappingException {
    sensitive = property.getAnnotation(Sensitive.class);
    return this;
  }
}