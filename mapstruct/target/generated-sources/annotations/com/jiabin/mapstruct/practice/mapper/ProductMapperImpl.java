package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.ProductDto;
import com.jiabin.mapstruct.practice.po.Product;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T19:52:46+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_401 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getCount() != null ) {
            productDto.setCount( product.getCount() );
        }
        else {
            productDto.setCount( 1 );
        }
        productDto.setName( product.getName() );
        productDto.setSubTitle( product.getSubTitle() );
        productDto.setBrandName( product.getBrandName() );
        productDto.setPrice( product.getPrice() );
        productDto.setCreateTime( product.getCreateTime() );

        productDto.setId( (long) -1L );
        productDto.setProductSn( UUID.randomUUID().toString() );

        return productDto;
    }
}
