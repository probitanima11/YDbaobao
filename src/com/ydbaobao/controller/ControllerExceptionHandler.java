package com.ydbaobao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.support.JSONResponseUtil;
import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.exception.JoinValidationException;
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
	
	// 회원가입시 유효성 검사 예외처리
	@ExceptionHandler(JoinValidationException.class)
	public ResponseEntity<Object> joinValidationException(JoinValidationException e) {
		return JSONResponseUtil.getJSONResponse(e.getExtractValidationMessages(), HttpStatus.PRECONDITION_FAILED);
	}
}