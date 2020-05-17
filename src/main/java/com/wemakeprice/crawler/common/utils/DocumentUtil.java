package com.wemakeprice.crawler.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * url로 부터 데이터를 가져오는 유틸 클래스
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
public class DocumentUtil {

	/**
	 * url 로 부터 document 객체를 얻는다.
	 *
	 * @param url
	 * @return Document
	 * @throws Exception
	 */
	public Document getDocument(String url) throws Exception {
		Document document = Jsoup.connect(url).timeout(10000).get();
		return document;
	}

	/**
	 * document 객체를 type에 따라 String 을 얻는다.
	 *
	 * @param document
	 * @param type (excludeTag : 태그 제외, allText : 모든 문자)
	 * @return String
	 */
	public String getDocumentText(Document document, String type) {
		String text = "";

		if (type.equals("excludeTag")) {
			text = document.text();
		} else if (type.equals("allText")) {
			text = document.outerHtml();
		}

		return text;
	}

}
