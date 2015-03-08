package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Hello;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@Api(value = "hello", description = "Hello world!")
public class HelloController {

    static final Logger logger = LogManager.getLogger(HelloController.class.getName());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get hello")
    public @ResponseBody Hello hello() {
        logger.info("inside HelloController");
        Hello hello = new Hello();
        hello.setHello("Hello");
        hello.setWorld("world!");
        return hello;
    }

}
