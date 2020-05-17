package com.wemakeprice.crawler.domain.response;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 크롤링 데이터 응답 response
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Getter
public class GetContentsResponse {

	// 몫
	private String quotient;

	// 나머지
	private String remainder;

	/**
	 * 몫, 나머지를 구한다.
	 *
	 * 출력묶음단위가 1 이면 몫 = 데이터, 나머지 없음
	 * 출력묶음단위가 데이터의 길이보다 크면 몫 없음, 나머지 = 데이터
	 *
	 * @param parsingText
	 * @param outputBundleUnit
	 */
	public GetContentsResponse(String parsingText, int outputBundleUnit) {

		// 데이터가 비어 있으면 몫, 나머지 모두 없음
		if(StringUtils.isEmpty(parsingText)) {
			quotient = "";
			remainder = "";
			return;
		}

		// 출력묶음단위가 1 이면 몫 = 데이터, 나머지 없음
		if(outputBundleUnit == 1) {
			quotient = parsingText;
			remainder = "";
			return;
		}

		// 출력묶음단위가 데이터의 길이보다 크면 몫 없음, 나머지 = 데이터
		if(outputBundleUnit > parsingText.length()) {
			quotient = "";
			remainder = parsingText;
			return;
		}

		int range = parsingText.length() - parsingText.length() % outputBundleUnit;
		quotient = parsingText.substring(0, range);
		remainder = parsingText.substring(range);
	}
}
