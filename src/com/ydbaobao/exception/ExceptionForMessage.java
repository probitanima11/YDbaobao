package com.ydbaobao.exception;

public class ExceptionForMessage extends Exception {
	private static final long serialVersionUID = 1L;

	public ExceptionForMessage() {
		super();
	}

	public ExceptionForMessage(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionForMessage(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionForMessage(String message) {
		super(message);
	}

	public ExceptionForMessage(Throwable cause) {
		super(cause);
	}

}
