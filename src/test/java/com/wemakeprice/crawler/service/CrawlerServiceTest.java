package com.wemakeprice.crawler.service;

import com.wemakeprice.crawler.common.domain.ResultMaster;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import com.wemakeprice.crawler.domain.response.GetContentsResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CrawlerServiceTest {

	private static CrawlerService crawlerService;
	private static String successUrl;
	private static String failUrl;
	private static String excludeTag;
	private static String allText;
	private static String failType;

	@BeforeClass
	public static void init() {
		crawlerService = new CrawlerService();
		successUrl = "http://www.crawler-test.com";
		failUrl = "ftp://www.crawler-test.com";
		excludeTag = "excludeTag";
		allText = "allText";
		failType = "123123";
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 출력 묶음 단위가 1일 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest1() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(excludeTag);
		getContentsRequest.setOutputBundleUnit(1);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 출력 묶음 단위가 추출된 문자열길이 보다 작을 경우(1 제외)
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest2() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(excludeTag);
		getContentsRequest.setOutputBundleUnit(100);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 출력 묶음 단위가 추출된 문자열길이 보다 클 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest3() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(excludeTag);
		getContentsRequest.setOutputBundleUnit(999999999);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 요청 타입이 allText 인 경우
	 * 출력 묶음 단위가 1일 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest4() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(allText);
		getContentsRequest.setOutputBundleUnit(1);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 요청 타입이 allText 인 경우
	 * 출력 묶음 단위가 추출된 문자열길이 보다 작을 경우(1 제외)
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest5() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(allText);
		getContentsRequest.setOutputBundleUnit(100);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 정상인 경우
	 * 요청 타입이 allText 인 경우
	 * 출력 묶음 단위가 추출된 문자열길이 보다 클 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest6() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(allText);
		getContentsRequest.setOutputBundleUnit(999999999);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		getContentsTest(getContentsRequest, resultMaster);
	}

	/**
	 * getContents 테스트 : 요청 데이터가 실패 경우
	 * 요청 url 이 http 요청이 아닌 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest7() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(failUrl);
		getContentsRequest.setType(allText);
		getContentsRequest.setOutputBundleUnit(999999999);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 500);
		Assert.assertNotNull(resultMaster.getMessage());
	}

	/**
	 * getContents 테스트 : 요청 데이터가 실패 경우
	 * 요청 tpye 이 잘못된 경우
	 *
	 * @throws Exception
	 */
	@Test
	public void getContentsTest8() {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl(successUrl);
		getContentsRequest.setType(failType);
		getContentsRequest.setOutputBundleUnit(999999999);

		ResultMaster<GetContentsResponse> resultMaster = crawlerService.getContents(getContentsRequest).getBody();

		Assert.assertEquals(resultMaster.getCode(), 200);
		Assert.assertEquals(resultMaster.getMessage(), "OK");

		Assert.assertEquals(resultMaster.getData().getQuotient(), "");
		Assert.assertEquals(resultMaster.getData().getRemainder(), "");
	}

	private void getContentsTest(GetContentsRequest getContentsRequest, ResultMaster<GetContentsResponse> resultMaster) {
		String quotient = resultMaster.getData().getQuotient();
		if(getContentsRequest.getOutputBundleUnit() == 1) {
			Assert.assertNotNull(resultMaster.getData().getQuotient());
			Assert.assertEquals(resultMaster.getData().getRemainder(), "");
		} else if(quotient.length() < getContentsRequest.getOutputBundleUnit()) {
			Assert.assertEquals(resultMaster.getData().getQuotient(), "");
			Assert.assertNotNull(resultMaster.getData().getRemainder());
		} else {
			Assert.assertNotNull(resultMaster.getData().getQuotient());
			Assert.assertNotNull(resultMaster.getData().getRemainder());
		}
	}
}
