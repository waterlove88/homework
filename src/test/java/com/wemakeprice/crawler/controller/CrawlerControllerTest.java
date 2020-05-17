package com.wemakeprice.crawler.controller;

import com.google.gson.Gson;
import com.wemakeprice.crawler.domain.request.GetContentsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CrawlerControllerTest {

	@Autowired
	MockMvc mockMvc;

	/**
	 * controller 성공 케이스 테스트 : 모든 요청 데이터가 정상
	 * 요청 type 이 excludeTag 일때
	 *
	 * @throws Exception
	 */
	@Test
	public void crawlerControllerTest1() throws Exception {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl("http://www.crawler-test.com");
		getContentsRequest.setType("excludeTag");
		getContentsRequest.setOutputBundleUnit(1);

		ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getContentsRequest))).andDo(print());

		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("data.quotient").exists())
				.andExpect(jsonPath("data.remainder").exists());

	}

	/**
	 * controller 성공 케이스 테스트 : 모든 요청 데이터가 정상
	 * 요청 type 이 allText 일때
	 *
	 * @throws Exception
	 */
	@Test
	public void crawlerControllerTest2() throws Exception {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl("http://www.crawler-test.com");
		getContentsRequest.setType("allText");
		getContentsRequest.setOutputBundleUnit(1);

		ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getContentsRequest))).andDo(print());

		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("data.quotient").exists())
				.andExpect(jsonPath("data.remainder").exists());

	}

	/**
	 * controller 실패 케이스 테스트 : url 이 http 가 아닐때
	 *
	 * @throws Exception
	 */
	@Test
	public void crawlerControllerTest3() throws Exception {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl("ftp://www.crawler-test.com");
		getContentsRequest.setType("excludeTag");
		getContentsRequest.setOutputBundleUnit(1);

		ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getContentsRequest))).andDo(print());

		resultActions
				.andExpect(status().isInternalServerError());

	}

	/**
	 * controller 실패 케이스 테스트 : 잘못된 요청 tpye을 전달 했을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void crawlerControllerTest4() throws Exception {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl("http://www.crawler-test.com");
		getContentsRequest.setType("excludeTag123");
		getContentsRequest.setOutputBundleUnit(1);

		ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getContentsRequest))).andDo(print());

		resultActions
				.andExpect(status().isBadRequest());

	}

	/**
	 * controller 실패 케이스 테스트 : 잘못된 요청 outputBundleUnit을 전달 했을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void crawlerControllerTest5() throws Exception {
		GetContentsRequest getContentsRequest = new GetContentsRequest();
		getContentsRequest.setUrl("http://www.crawler-test.com");
		getContentsRequest.setType("excludeTag");
		getContentsRequest.setOutputBundleUnit(0);

		ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getContentsRequest))).andDo(print());

		resultActions
				.andExpect(status().isBadRequest());

	}
}
