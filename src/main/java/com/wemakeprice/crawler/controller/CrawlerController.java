package com.wemakeprice.crawler.controller;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import com.wemakeprice.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 크롤링 controller
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {

	private final CrawlerService crawlerService;

	/**
	 * 크롤링 데이터 파싱
	 *
	 * @param getContentsRequest
	 * @return ResponseEntity
	 */
	@PostMapping("/contents")
	public ResponseEntity<ResultMaster> getContents(@Valid @RequestBody GetContentsRequest getContentsRequest) {

		log.debug("getContentsRequest : " + getContentsRequest);

		return crawlerService.getContents(getContentsRequest);

	}
}
