package com.wemakeprice.crawler.common.domain;

import lombok.Getter;

/**
 * API 응답 결과에 대한 데이터/코드/메세지를 포함하는 모델 클래스
 *
 * @param <T> the type parameter
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Getter
public class ResultMaster<T> {

	private T data;
	private int code;
	private String message;

	public ResultMaster(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultMaster(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
