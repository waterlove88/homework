package com.wemakeprice.crawler.domain.request;

import com.wemakeprice.crawler.validation.TypeCheck;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;

@Getter
@Setter
public class GetContentsRequest {

	@URL
	private String url;

	@TypeCheck
	private String type;

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
