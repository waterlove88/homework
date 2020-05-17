package com.wemakeprice.crawler.service;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import com.wemakeprice.crawler.common.utils.DocumentUtil;
import com.wemakeprice.crawler.domain.ParsingText;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import com.wemakeprice.crawler.domain.response.GetContentsResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CrawlerService {

	public ResultMaster<GetContentsResponse> getContents(GetContentsRequest getContentsRequest) {
		DocumentUtil documentUtil = new DocumentUtil();
		String url = getContentsRequest.getUrl();
		String type = getContentsRequest.getType();
		int outputBundleUnit = getContentsRequest.getOutputBundleUnit();

		Document document;
		// 해당 url에 데이터를 얻어옴
		try {
			document = documentUtil.getDocument(url);
		} catch (Exception e) {
			e.printStackTrace();
			// return
			return new ResultMaster<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}

		// 타입에 따라 파싱처리
		// 알파벳은 AaBbCc 순으로, 숫자는 01234 순으로 정렬
		ParsingText parsingText = documentUtil.getDocumentText(document, type);

		return new ResultMaster<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), new GetContentsResponse(parsingText.getParsingText(), outputBundleUnit));
		// 그리고 교차출력(영어, 숫자, 영어, 숫자 순)
		// 묶음 단위로 출력
		// 1 이면 몫 = 문장, 나머지 0
		// 문장보다 크면 몫 = 0, 나머지 = 문장

	}
}
