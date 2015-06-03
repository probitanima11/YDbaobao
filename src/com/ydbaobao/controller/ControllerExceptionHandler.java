package com.ydbaobao.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.model.Customer;

@ControllerAdvice
public class ControllerExceptionHandler {
	// 메시지 전달을 위한 exception	
	@ExceptionHandler(ExceptionForMessage.class)
	public ModelAndView exceptionForMessage(ExceptionForMessage e) {
		ModelAndView mav = new ModelAndView(e.getLocation());
		mav.addObject("message", e.getMessage());
		mav.addObject("customer", new Customer());
		return mav;
	}
}