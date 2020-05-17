package com.wemakeprice.crawler.exception;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@RestControllerAdvice
public class ControllerExceptionAdviser {

	@ExceptionHandler({Exception.class})
	public ResultMaster controllerExceptionHandler(Exception exception) {

		if (exception instanceof BindException || exception instanceof MethodArgumentNotValidException) {
			return new ResultMaster(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		} else if (exception instanceof HttpRequestMethodNotSupportedException) {
			return new ResultMaster(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		} else if (exception instanceof NoHandlerFoundException) {
			return new ResultMaster(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}

		return new ResultMaster(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
	}
}
