package com.wemakeprice.crawler.utils;

import com.wemakeprice.crawler.common.utils.DocumentUtil;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DocumentUilsTest {

	private static DocumentUtil documentUtil;
	private static String successUrl;
	private static String failUrl;

	@BeforeClass
	public static void init() {
		documentUtil = new DocumentUtil();
		successUrl = "http://www.crawler-test.com";
		failUrl = "ftp://www.crawler-test.com";
	}

	/**
	 * getDocument 테스트 : 요청 데이터가 정상인 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getDocumentSuccessTest() {
		try {
			Assert.assertNotNull(documentUtil.getDocument(successUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDocument 테스트 : 요청 데이터가 실패인 경우
	 * 요청 url 이 http가 아닌 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getDocumentFailTest() {
		try {
			Assert.assertNull(documentUtil.getDocument(failUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDocumentText 테스트 : 요청 데이터가 정상 경우
	 * 요청 타입이 excludeTag인 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getDocumentTextSuccessTest1() {
		try {
			Document document = documentUtil.getDocument(successUrl);
			Assert.assertNotEquals(documentUtil.getDocumentText(document, "excludeTag"), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDocumentText 테스트 : 요청 데이터가 정상 경우
	 * 요청 타입이 allText인 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getDocumentTextSuccessTest2() {
		try {
			Document document = documentUtil.getDocument(successUrl);
			Assert.assertNotEquals(documentUtil.getDocumentText(document, "allText"), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDocumentText 테스트 : 요청 데이터가 정상 경우
	 * 요청 타입이 잘못된 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getDocumentTextFailTest() {
		try {
			Document document = documentUtil.getDocument(successUrl);
			Assert.assertEquals(documentUtil.getDocumentText(document, ""), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
