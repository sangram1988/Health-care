package com.healthcare.exception;

public class HealthcareException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private Throwable cause;

	public HealthcareException() {
	}

	public HealthcareException(String message) {
		super(message);
		this.message = message;
	}

	public HealthcareException(String message, Throwable cause) {
		super();
		this.message = message;
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
