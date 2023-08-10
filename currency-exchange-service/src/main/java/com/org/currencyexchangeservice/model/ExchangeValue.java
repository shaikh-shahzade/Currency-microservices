package com.org.currencyexchangeservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "exchangeValue")
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="Basecurrency")
    private String from;
    @Column(name="Destcurrency")
    private String to;
    private BigDecimal conversionMultiple;
    private Integer port;
}
