package com.org.currencyexchangeservice.controller;

import com.org.currencyexchangeservice.model.ExchangeValue;
import com.org.currencyexchangeservice.repository.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeRepo exchangeRepo;
    @GetMapping("from/{from}/to/{to}")
    public ExchangeValue getExchangedValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = exchangeRepo.findOneByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;
    }

    @GetMapping("save")
    public List<ExchangeValue> exchangeValueList() {
        //Taking hardcoded Values

        int port = Integer.parseInt(environment.getProperty("local.server.port"));
        List<ExchangeValue> lst = Arrays.asList(
                ExchangeValue.builder().to("USD").from("INR").port(port).conversionMultiple(new BigDecimal(78)).build(),
                ExchangeValue.builder().to("INR").from("USD").port(port).conversionMultiple(new BigDecimal(0.75)).build(),
                ExchangeValue.builder().to("EUR").from("INR").port(port).conversionMultiple(new BigDecimal(177)).build(),
                ExchangeValue.builder().to("USD").from("EUR").port(port).conversionMultiple(new BigDecimal(23)).build(),
                ExchangeValue.builder().to("INR").from("EUR").port(port).conversionMultiple(new BigDecimal(42)).build()
                );
        lst = exchangeRepo.saveAll(lst);

        return lst;
    }

}
