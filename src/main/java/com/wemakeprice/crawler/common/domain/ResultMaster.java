package com.wemakeprice.crawler.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
