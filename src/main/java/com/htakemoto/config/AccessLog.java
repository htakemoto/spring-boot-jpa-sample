package com.htakemoto.config;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
public class AccessLog {
	
	private static final String ACCESS_REQUEST_LOG_MESSAGE = "[{}] {}";
	
	@Before("within(com.htakemoto.rest.controller.*)")
	public void log() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String requestURL = getFullURL(request);
		String requestMethod = request.getMethod();
		log.info(ACCESS_REQUEST_LOG_MESSAGE, requestMethod, requestURL);
	}
	
	private static String getFullURL(HttpServletRequest request) {
		String requestPath = request.getRequestURI().substring(request.getContextPath().length());
		String queryString = request.getQueryString();
		return queryString == null ? requestPath : requestPath + "?" + queryString;
	}
}
