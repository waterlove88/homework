package com.wemakeprice.crawler.common.utils;

import com.wemakeprice.crawler.domain.ParsingText;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtil {

	// 해당 url에 데이터를 얻어옴
	public Document getDocument(String url) throws Exception {
		Document document = Jsoup.connect(url).timeout(10000).get();
		return document;
	}

	// 타입에 따라 파싱처리
	public ParsingText getDocumentText(Document document, String type) {
		String text = "";

		if (type.equals("excludeTag")) {
			text = document.text();
		} else if (type.equals("allText")) {
			text = document.outerHtml();
		}

		return new ParsingText(text);
	}

}
