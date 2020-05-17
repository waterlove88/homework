package com.wemakeprice.crawler.exception;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

/**
 * request 요청 관련 공통 에러처리 클랙스
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@RestControllerAdvice
public class ControllerExceptionAdviser {

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ResultMaster> controllerExceptionHandler(Exception exception) {

		// 잘못된 요청 400
		if (exception instanceof BindException || exception instanceof MethodArgumentNotValidException
				|| exception instanceof HttpMediaTypeNotSupportedException) {
			return new ResponseEntity(
					new ResultMaster(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())
					, HttpStatus.BAD_REQUEST);

		// 잘못된 메소드 요청 405
		} else if (exception instanceof HttpRequestMethodNotSupportedException) {
			return new ResponseEntity(
					new ResultMaster(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
					, HttpStatus.METHOD_NOT_ALLOWED);

		// 없는 request 요청 404
		} else if (exception instanceof NoHandlerFoundException) {
			return new ResponseEntity(
					new ResultMaster(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase())
					, HttpStatus.NOT_FOUND);
		}

		// 기타 에러는 500 처리
		return new ResponseEntity(
				new ResultMaster(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage())
				, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
