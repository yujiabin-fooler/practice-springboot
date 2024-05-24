package com.jiabin.wechat.mp.practice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponRequestDTO {
	private String code;
	private BigDecimal value;
	private LocalDateTime expiryDate;
}