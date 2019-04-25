package com.bananes.error;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Let Spring handle the exception, we just override the status code
	@ExceptionHandler(DaysDiffException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
	}

	@ExceptionHandler(QuantityIlegalException.class)
	public void springUnSupportedFieldPatch(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
	}

}
