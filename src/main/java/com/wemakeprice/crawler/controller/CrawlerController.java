package com.wemakeprice.crawler.controller;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import com.wemakeprice.crawler.domain.response.GetContentsResponse;
import com.wemakeprice.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {

	private final CrawlerService crawlerService;

	@PostMapping("/contents")
	public ResultMaster<GetContentsResponse> getContents(@Valid @RequestBody GetContentsRequest getContentsRequest) {

		log.debug("getContentsRequest : " + getContentsRequest);

		return crawlerService.getContents(getContentsRequest);

	}
}
