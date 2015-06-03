package com.ydbaobao.exception;

public class ExceptionForMessage extends Exception {
	private static final long serialVersionUID = 1L;
	private String location;

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
	
	public ExceptionForMessage(String message, String location) {
		super(message);
		this.location = location;
	}

	public ExceptionForMessage(Throwable cause) {
		super(cause);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
