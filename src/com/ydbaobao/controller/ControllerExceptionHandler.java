package com.ydbaobao.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.exception.ExceptionForMessage;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ExceptionForMessage.class)
	public ModelAndView notExistedUserIdException(ExceptionForMessage e) {
		ModelAndView mav = new ModelAndView("/customer/create");
		mav.addObject("message", e.getMessage());
		return mav;
	}
}
