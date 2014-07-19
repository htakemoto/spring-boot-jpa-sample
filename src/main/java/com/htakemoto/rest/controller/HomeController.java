package com.htakemoto.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String home() {
        return "Welcome to home!";
    }
}
