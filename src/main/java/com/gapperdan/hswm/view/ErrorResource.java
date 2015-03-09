package com.gapperdan.hswm.view;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

public class ErrorResource {

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;


    @Getter
    @Setter
    private String ref;
    public ErrorResource() {
    }

    public ErrorResource(String code, String message) {
        this.code = code;
        this.message = message;
        this.ref = RandomStringUtils.randomAlphanumeric(10);
    }

    public void generateRefId() {
        this.ref = RandomStringUtils.randomAlphanumeric(10);
    }
}