package com.gapperdan.hswm.view;

import lombok.Getter;
import lombok.Setter;

public class ErrorResource {

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;

    public ErrorResource() {
    }

    public ErrorResource(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
