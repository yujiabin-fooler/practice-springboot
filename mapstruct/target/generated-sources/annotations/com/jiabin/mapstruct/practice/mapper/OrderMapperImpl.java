package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.OrderDto;
import com.jiabin.mapstruct.practice.dto.ProductDto;
import com.jiabin.mapstruct.practice.po.Order;
import com.jiabin.mapstruct.practice.po.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T19:52:46+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_401 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    private final MemberMapper memberMapper = Mappers.getMapper( MemberMapper.class );
    private final ProductMapper productMapper = Mappers.getMapper( ProductMapper.class );

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setMemberDto( memberMapper.toDto( order.getMember() ) );
        orderDto.setProductDtoList( productListToProductDtoList( order.getProductList() ) );
        orderDto.setId( order.getId() );
        orderDto.setOrderSn( order.getOrderSn() );
        orderDto.setCreateTime( order.getCreateTime() );
        orderDto.setReceiverAddress( order.getReceiverAddress() );

        return orderDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productMapper.toDto( product ) );
        }

        return list1;
    }
}
