package com.wemakeprice.crawler.domain.request;

import com.wemakeprice.crawler.validation.TypeCheck;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;

/**
 * 크롤링 데이터 요청 request
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Getter
@Setter
public class GetContentsRequest {

	// url
	@URL
	private String url;

	// type (excludeTag, allText)
	@TypeCheck
	private String type;

	// 출력묶음단위
	@Min(1)
	private int outputBundleUnit;

	@Override
	public String toString() {
		return "GetContentsRequest{" +
				"url='" + url + '\'' +
				", type='" + type + '\'' +
				", outputBundleUnit=" + outputBundleUnit +
				'}';
	}
}
