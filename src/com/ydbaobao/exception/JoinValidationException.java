package com.ydbaobao.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class JoinValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Object extractValidationMessages;

	public JoinValidationException() {
		super();
	}

	public JoinValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JoinValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JoinValidationException(String message) {
		super(message);
	}

	public JoinValidationException(Throwable cause) {
		super(cause);
	}

	public JoinValidationException(BindingResult extractValidationMessages) {
		this.extractValidationMessages = this.getErrorMessages(extractValidationMessages);
	}
	
	public Object getExtractValidationMessages() {
		return extractValidationMessages;
	}

	public void setExtractValidationMessages(Object extractValidationMessages) {
		this.extractValidationMessages = extractValidationMessages;
	}

	private List<String> getErrorMessages(BindingResult result) {
		return result.getAllErrors().stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
	}
}
