package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.ProductDto;
import com.jiabin.mapstruct.practice.exception.ProductValidatorException;
import com.jiabin.mapstruct.practice.po.Product;
import com.jiabin.mapstruct.practice.validator.ProductValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

/**
 * 商品对象映射（处理映射异常）
 * @author jiabin.yu 2021/10/21.
 */
@Mapper(uses = {ProductValidator.class},imports = {UUID.class})
public interface ProductExceptionMapper {
    ProductExceptionMapper INSTANCE = Mappers.getMapper(ProductExceptionMapper.class);

    @Mapping(target = "id",constant = "-1L")
    @Mapping(source = "count",target = "count",defaultValue = "1")
    @Mapping(target = "productSn",expression = "java(UUID.randomUUID().toString())")
    ProductDto toDto(Product product) throws ProductValidatorException;
}
