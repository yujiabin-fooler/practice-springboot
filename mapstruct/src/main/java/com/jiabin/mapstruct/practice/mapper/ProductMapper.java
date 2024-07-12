package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.ProductDto;
import com.jiabin.mapstruct.practice.po.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

/**
 * 商品对象映射
 * @author jiabin.yu 2021/10/21.
 */
@Mapper(imports = {UUID.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id",constant = "-1L")
    @Mapping(source = "count",target = "count",defaultValue = "1")
    @Mapping(target = "productSn",expression = "java(UUID.randomUUID().toString())")
    ProductDto toDto(Product product);
}
