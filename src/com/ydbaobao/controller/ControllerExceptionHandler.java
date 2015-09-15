package com.ydbaobao.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ydbaobao.domain.Customer;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.JoinValidationException;

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
	
	// 회원가입시 유효성 검사 예외처리
	@ExceptionHandler(JoinValidationException.class)
	public ModelAndView joinValidationException(JoinValidationException e) {
		ModelAndView mav = new ModelAndView("form");
		mav.addObject("customer", new Customer());
		mav.addObject("validMessages", e.getExtractValidationMessages());
		return mav;
//		return JSONResponseUtil.getJSONResponse(e.getExtractValidationMessages(), HttpStatus.PRECONDITION_FAILED);
	}
}