package com.thalasoft.butik.data.exception;

@SuppressWarnings("serial")
public class CannotDeleteEntityException extends EnrichableException {

	public CannotDeleteEntityException() {
		super("The entity could not be deleted.");
	}

	public CannotDeleteEntityException(String message) {
		super(message);
	}

}