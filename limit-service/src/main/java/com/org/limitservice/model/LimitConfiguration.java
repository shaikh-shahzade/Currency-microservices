package com.org.limitservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitConfiguration {

    private Integer min;
    private Integer max;

}
