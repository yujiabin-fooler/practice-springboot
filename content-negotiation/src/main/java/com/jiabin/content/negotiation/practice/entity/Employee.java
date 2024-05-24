package com.jiabin.content.negotiation.practice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="cn_employee")
@XmlRootElement
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal salary;
}