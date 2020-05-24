package com.bsu.pt.exam.exception;

public class StudentValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentValidationException(String msg, Throwable t) {
		super(msg, t);
	}

	public StudentValidationException(String msg) {
		super(msg);
	}
}
