package com.jiabin.encrypt.filed.likequery.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.google.common.io.LineProcessor;

public final class CharDigestLikeEncryptAlgorithm {
    
    private static final String DELTA = "delta";
    
    private static final String MASK = "mask";
    
    private static final String START = "start";
    
    private static final String DICT = "dict";
    
    private static final int DEFAULT_DELTA = 1;
    
    private static final int DEFAULT_MASK = 0b1111_0111_1101;
    
    private static final int DEFAULT_START = 0x4e00;
    
    private static final int MAX_NUMERIC_LETTER_CHAR = 255;
    
    private int delta;
    
    private int mask;
    
    private int start;
    
    private Map<Character, Integer> charIndexes;
    
    public void init(final Properties props) {
        delta = createDelta(props);
        mask = createMask(props);
        start = createStart(props);
        charIndexes = createCharIndexes(props);
    }
    
    private int createDelta(final Properties props) {
        if (props.containsKey(DELTA)) {
            String delta = props.getProperty(DELTA);
            try {
                return Integer.parseInt(delta);
            } catch (final NumberFormatException ex) {
                throw new RuntimeException("数字格式化错误, delta 偏移量必须是数字", ex);
            }
        }
        return DEFAULT_DELTA;
    }
    
    private int createMask(final Properties props) {
        if (props.containsKey(MASK)) {
            String mask = props.getProperty(MASK);
            try {
                return Integer.parseInt(mask);
            } catch (final NumberFormatException ex) {
            	throw new RuntimeException("数字格式化错误, mask 掩码必须是数字类型", ex);
            }
        }
        return DEFAULT_MASK;
    }
    
    private int createStart(final Properties props) {
        if (props.containsKey(START)) {
            String start = props.getProperty(START);
            try {
                return Integer.parseInt(start);
            } catch (final NumberFormatException ex) {
            	throw new RuntimeException("数字格式化错误, start 起始索引必须是数字类型", ex);
            }
        }
        return DEFAULT_START;
    }
    
    private Map<Character, Integer> createCharIndexes(final Properties props) {
        String dictContent = props.containsKey(DICT) && !Strings.isNullOrEmpty(props.getProperty(DICT)) ? props.getProperty(DICT) : initDefaultDict();
        Map<Character, Integer> result = new HashMap<>(dictContent.length(), 1);
        for (int index = 0; index < dictContent.length(); index++) {
            result.put(dictContent.charAt(index), index);
        }
        return result;
    }
    
    private String initDefaultDict() {
        InputStream inputStream = CharDigestLikeEncryptAlgorithm.class.getClassLoader().getResourceAsStream("common_chinese_character.dict");
        LineProcessor<String> lineProcessor = new LineProcessor<String>() {
            
            private final StringBuilder builder = new StringBuilder();
            
            @Override
            public boolean processLine(final String line) {
                if (line.startsWith("#") || 0 == line.length()) {
                    return true;
                } else {
                    builder.append(line);
                    return false;
                }
            }
            
            @Override
            public String getResult() {
                return builder.toString();
            }
        };
        try {
			return CharStreams.readLines(new InputStreamReader(inputStream, Charsets.UTF_8), lineProcessor);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
    }
    
    public String encrypt(final Object plainValue) {
        return null == plainValue ? null : digest(String.valueOf(plainValue));
    }
    
    private String digest(final String plainValue) {
        StringBuilder result = new StringBuilder(plainValue.length());
        for (char each : plainValue.toCharArray()) {
            char maskedChar = getMaskedChar(each);
            if ('%' == maskedChar || '_' == maskedChar) {
                result.append(each);
            } else {
                result.append(maskedChar);
            }
        }
        return result.toString();
    }
    
    private char getMaskedChar(final char originalChar) {
        if ('%' == originalChar || '_' == originalChar) {
            return originalChar;
        }
        if (originalChar <= MAX_NUMERIC_LETTER_CHAR) {
            return (char) ((originalChar + delta) & mask);
        }
        if (charIndexes.containsKey(originalChar)) {
            return (char) (((charIndexes.get(originalChar) + delta) & mask) + start);
        }
        return (char) (((originalChar + delta) & mask) + start);
    }
    
    public String getType() {
        return "CHAR_DIGEST_LIKE";
    }
}
