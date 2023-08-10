package com.org.currencyexchangeservice.repository;

import com.org.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepo extends JpaRepository<ExchangeValue,Long> {
    public ExchangeValue findOneByFromAndTo(String from,String to);
}
