package com.gapperdan.hswm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel
@Document(collection = "countries")
public class Country {

    @Id
    @JsonIgnore
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @ApiModelProperty(required = true, value = "country name", position = 1)
    @NotEmpty(message = "Field 'name' should not be empty")
    private String name;

    @Getter
    @Setter
    @ApiModelProperty(required = true, value = "2-letter country code", position = 2)
    @NotEmpty(message = "Field 'code' should not be empty")
    private String code;

    @Getter
    @Setter
    @ApiModelProperty(required = true, value = "country capital", position = 3)
    @NotEmpty(message = "Field 'capital' should not be empty")
    private String capital;

    @Getter
    @Setter
    @ApiModelProperty(required = true, value = "country population", position = 4)
    private long population;
}
