package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.MemberDto;
import com.jiabin.mapstruct.practice.dto.MemberOrderDto;
import com.jiabin.mapstruct.practice.po.Member;
import com.jiabin.mapstruct.practice.po.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员对象映射
 * @author jiabin.yu 2021/10/21.
 */
@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "phone",target = "phoneNumber")
    @Mapping(source = "birthday",target = "birthday",dateFormat = "yyyy-MM-dd")
    MemberDto toDto(Member member);

    @Mapping(source = "phone",target = "phoneNumber")
    @Mapping(source = "birthday",target = "birthday",dateFormat = "yyyy-MM-dd")
    List<MemberDto> toDtoList(List<Member> list);

    @Mapping(source = "member.phone",target = "phoneNumber")
    @Mapping(source = "member.birthday",target = "birthday",dateFormat = "yyyy-MM-dd")
    @Mapping(source = "member.id",target = "id")
    @Mapping(source = "order.orderSn", target = "orderSn")
    @Mapping(source = "order.receiverAddress", target = "receiverAddress")
    MemberOrderDto toMemberOrderDto(Member member, Order order);
}
