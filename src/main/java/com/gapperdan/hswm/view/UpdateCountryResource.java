package com.gapperdan.hswm.view;

import com.wordnik.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
public class UpdateCountryResource {

    public UpdateCountryResource() {
    }

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String capital;

    @Getter
    @Setter
    private long population;

}
