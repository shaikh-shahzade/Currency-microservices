package com.org.controller;

import com.org.model.CurrencyConversionResponse;
import lombok.NoArgsConstructor;
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

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse getConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String , Object> hmap = new HashMap<String , Object>();
        hmap.put("from",from);
        hmap.put("to",to);
        hmap.put("quantity",quantity);
        ResponseEntity<CurrencyConversionResponse> coversionRes =  restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/USD/to/INR",CurrencyConversionResponse.class,hmap);
        return coversionRes.getBody();
    }

}
