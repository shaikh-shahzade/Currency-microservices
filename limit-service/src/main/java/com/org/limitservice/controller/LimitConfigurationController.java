package com.org.limitservice.controller;

import com.org.limitservice.model.LimitConfiguration;
import com.org.limitservice.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {
    @Autowired
    private PropertiesConfig propertiesConfig;
    @GetMapping("limits")
    public LimitConfiguration getLimitConfiguration()
    {
        return new LimitConfiguration(propertiesConfig.getMinimum(),propertiesConfig.getMaximum());
    }

}
