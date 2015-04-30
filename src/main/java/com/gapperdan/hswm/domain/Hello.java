package com.gapperdan.hswm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hello {

    @Getter
    @Setter
    private String hello;

    @Getter
    @Setter
    private String world;
}
