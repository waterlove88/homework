package com.wemakeprice.crawler.service;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import com.wemakeprice.crawler.common.utils.DocumentUtil;
import com.wemakeprice.crawler.domain.ParsingText;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import com.wemakeprice.crawler.domain.response.GetContentsResponse;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 크롤링 service
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Service
public class CrawlerService {

	/**
	 * 요청 request 로 크롤링 처리
	 *
	 * @param getContentsRequest
	 * @return ResponseEntity<ResultMaster>
	 */
	public ResponseEntity<ResultMaster> getContents(GetContentsRequest getContentsRequest) {
		DocumentUtil documentUtil = new DocumentUtil();
		String url = getContentsRequest.getUrl();
		String type = getContentsRequest.getType();
		int outputBundleUnit = getContentsRequest.getOutputBundleUnit();

		Document document;
		// 해당 url에 데이터를 얻어옴
		try {
			document = documentUtil.getDocument(url);
		} catch (Exception e) {
			return new ResponseEntity(
					new ResultMaster<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage())
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 타입에 따라 파싱처리
		// 알파벳은 사전+대문자우선 순으로, 숫자는 01234 순으로 정렬
		ParsingText parsingText = new ParsingText(documentUtil.getDocumentText(document, type));

		// 추출된 데이터를 통해 교차출력 데이터를 가져와 응답 객체 생성 후 리턴
		return new ResponseEntity(
				new ResultMaster<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), new GetContentsResponse(parsingText.getParsingText(), outputBundleUnit))
				, HttpStatus.OK);

	}
}
