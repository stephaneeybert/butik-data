package com.thalasoft.butik.data.exception;

@SuppressWarnings("serial")
public class NoEntitiesFoundException extends EnrichableException {

	public NoEntitiesFoundException() {
		super("No entities were found.");
	}

	public NoEntitiesFoundException(String message) {
		super(message);
	}

}