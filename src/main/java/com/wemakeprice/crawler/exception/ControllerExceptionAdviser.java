package com.wemakeprice.crawler.exception;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

/**
 * request 요청 관련 공통 에러처리 클랙스
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@RestControllerAdvice
public class ControllerExceptionAdviser {

	/**
	 * 기타 에러 처리 : 500
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ResultMaster> controllerExceptionHandler(Exception exception) {
		return new ResponseEntity(
				new ResultMaster(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage())
				, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 잘못된 요청 처리 : 400
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<ResultMaster> bindExceptionHandler(Exception exception) {
		return new ResponseEntity(
				new ResultMaster(HttpStatus.BAD_REQUEST.value(), exception.getMessage())
				, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 잘못된 메소드 요청 : 405
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResultMaster> notSupportExceptionHandler(Exception exception) {
		return new ResponseEntity(
				new ResultMaster(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage())
				, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
