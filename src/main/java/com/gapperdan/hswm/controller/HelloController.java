package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Hello;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@Controller
@EnableWebMvc
@Log4j2
@Api(value = "hello", description = "Hello world!")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get hello")
    public @ResponseBody Hello hello() {
        log.info("Reference Id ("+ RandomStringUtils.randomAlphanumeric(10)+"): inside HelloController");
        Hello hello = new Hello();
        hello.setHello("Hello");
        hello.setWorld("world!");
        return hello;
    }

    @RequestMapping(value = "/hello/client", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "rest client example")
    public @ResponseBody Hello helloRestClient() {

        //set Accept Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
        HttpEntity<?> requestEntity = new HttpEntity<Object>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String restEndpoint = "http://localhost:8080/hswm/hello";
        //Hello hello = restTemplate.getForObject(restEndpoint, Hello.class);
        ResponseEntity<Hello> responseEntity = restTemplate.exchange(restEndpoint, HttpMethod.GET, requestEntity, Hello.class);
        Hello hello = responseEntity.getBody();
        hello.setHello(hello.getHello() + "2");
        hello.setWorld(hello.getWorld() + "2");
        return hello;
    }
}
