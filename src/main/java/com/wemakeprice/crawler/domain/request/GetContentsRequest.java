package com.wemakeprice.crawler.domain.request;

import com.wemakeprice.crawler.validation.TypeCheck;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;

/**
 * 크롤링 데이터 요청 request
 * <p>
 * url : 크롤링 파싱 url
 * type : 크롤링 파싱타입(excludeTag, allText)
 * outputBundleUnit : 출력묶음단위
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Getter
@Setter
public class GetContentsRequest {

	@URL
	private String url;

	@TypeCheck
	private String type;

	@Min(1)
	private int outputBundleUnit;
}
