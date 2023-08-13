package com.org.controller;

import com.org.config.CurrencyExchangeProxy;
import com.org.model.CurrencyConversionResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;
    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse getConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String , Object> hmap = new HashMap<String , Object>();
        hmap.put("from",from);
        hmap.put("to",to);
        ResponseEntity<CurrencyConversionResponse> conversionRes =  restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversionResponse.class,hmap);
        CurrencyConversionResponse currRes = conversionRes.getBody();
        currRes.setQuantity(quantity);
        currRes.setTotalCalculatedAmount(currRes.getConversionMultiple().multiply(quantity));
        return currRes;
    }

    @GetMapping("v2/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse getConversionV2(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
    {

        CurrencyConversionResponse currRes = currencyExchangeProxy.getCurrency(from,to);
        currRes.setQuantity(quantity);
        currRes.setTotalCalculatedAmount(currRes.getConversionMultiple().multiply(quantity));
        return currRes;
    }
}
