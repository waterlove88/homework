package com.wemakeprice.crawler.domain.response;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class GetContentsResponse {
	private String quotient;
	private String remainder;

	public GetContentsResponse(String parsingText, int outputBundleUnit) {
		if(StringUtils.isEmpty(parsingText)) {
			quotient = "";
			remainder = "";
			return;
		}

		if(outputBundleUnit == 1) {
			quotient = parsingText;
			remainder = "";
			return;
		}

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
