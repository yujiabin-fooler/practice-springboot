package com.jiabin.mapstruct.practice.mapper;

import com.jiabin.mapstruct.practice.dto.MemberDto;
import com.jiabin.mapstruct.practice.dto.MemberOrderDto;
import com.jiabin.mapstruct.practice.po.Member;
import com.jiabin.mapstruct.practice.po.Order;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T19:52:46+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_401 (Oracle Corporation)"
)
@Component
public class MemberSpringMapperImpl implements MemberSpringMapper {

    @Override
    public MemberDto toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto memberDto = new MemberDto();

        memberDto.setPhoneNumber( member.getPhone() );
        if ( member.getBirthday() != null ) {
            memberDto.setBirthday( new SimpleDateFormat( "yyyy-MM-dd" ).format( member.getBirthday() ) );
        }
        memberDto.setId( member.getId() );
        memberDto.setUsername( member.getUsername() );
        memberDto.setPassword( member.getPassword() );
        memberDto.setNickname( member.getNickname() );
        memberDto.setIcon( member.getIcon() );
        memberDto.setGender( member.getGender() );

        return memberDto;
    }

    @Override
    public List<MemberDto> toDtoList(List<Member> list) {
        if ( list == null ) {
            return null;
        }

        List<MemberDto> list1 = new ArrayList<MemberDto>( list.size() );
        for ( Member member : list ) {
            list1.add( toDto( member ) );
        }

        return list1;
    }

    @Override
    public MemberOrderDto toMemberOrderDto(Member member, Order order) {
        if ( member == null && order == null ) {
            return null;
        }

        MemberOrderDto memberOrderDto = new MemberOrderDto();

        if ( member != null ) {
            memberOrderDto.setPhoneNumber( member.getPhone() );
            if ( member.getBirthday() != null ) {
                memberOrderDto.setBirthday( new SimpleDateFormat( "yyyy-MM-dd" ).format( member.getBirthday() ) );
            }
            memberOrderDto.setId( member.getId() );
            memberOrderDto.setUsername( member.getUsername() );
            memberOrderDto.setPassword( member.getPassword() );
            memberOrderDto.setNickname( member.getNickname() );
            memberOrderDto.setIcon( member.getIcon() );
            memberOrderDto.setGender( member.getGender() );
        }
        if ( order != null ) {
            memberOrderDto.setOrderSn( order.getOrderSn() );
            memberOrderDto.setReceiverAddress( order.getReceiverAddress() );
        }

        return memberOrderDto;
    }
}
