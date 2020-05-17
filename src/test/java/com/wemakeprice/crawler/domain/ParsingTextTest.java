package com.wemakeprice.crawler.domain;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class ParsingTextTest {

	private static ParsingText parsingText;
	private static String emptyText;
	private static String successText;
	private static String failText;
	private static String onlyAlphabet;
	private static String onlyNumber;

	@BeforeClass
	public static void init() {
		emptyText = "";
		successText = "aaaAAAAb111bbBBBbCC2222cccCCa1010aaCC";
		failText = "%&^%^#$%#@##$$$$";
		onlyAlphabet = "aaaaAcAAaCcccCCcbBcaBcBbAbbB";
		onlyNumber = "102937049112333445";

	}

	private void parsingTest(String text, ParsingText parsingText) {
		if(StringUtils.isEmpty(text)) {
			Assert.assertEquals(parsingText.getAlphabet(), "");
			Assert.assertEquals(parsingText.getNumber(), "");
		} else {
			Assert.assertNotNull(parsingText.getAlphabet());
			Assert.assertNotNull(parsingText.getNumber());
		}
	}

	/**
	 * parsingTest 테스트
	 * case 1 : 크롤링 데이터가 비었을 때
	 * case 2 : 크롤링 데이터가 정상일 때
	 * case 3 : 크롤링 데이터가 있으나 영문,숫자가 없을 때
	 * case 4 : 크롤링 데이터가 영문만 있을 때
	 * case 5 : 크롤링 데이터가 숫자만 있을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void parsingTextTest() {
		parsingText = new ParsingText(emptyText);
		parsingTest(emptyText, parsingText);

		parsingText = new ParsingText(successText);
		parsingTest(successText, parsingText);

		parsingText = new ParsingText(failText);
		parsingTest(failText, parsingText);

		parsingText = new ParsingText(onlyAlphabet);
		parsingTest(onlyAlphabet, parsingText);

		parsingText = new ParsingText(onlyNumber);
		parsingTest(onlyNumber, parsingText);
	}


	/**
	 * getAlphabet 테스트
	 * case 1 : 크롤링 데이터가 비었을 때
	 * case 2 : 크롤링 데이터가 정상일 때
	 * case 3 : 크롤링 데이터가 있으나 영문,숫자가 없을 때
	 * case 4 : 크롤링 데이터가 영문만 있을 때
	 * case 5 : 크롤링 데이터가 숫자만 있을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void setAlphabetTest() {
		try {
			parsingText = new ParsingText(emptyText);
			Assert.assertEquals(parsingText.getAlphabet(), "");

			// aaaAAAAb111bbBBBbCC2222cccCCa1010aaCC
			// AAAAaaaaaaBBBbbbbCCCCCCccc
			parsingText = new ParsingText(successText);
			Assert.assertEquals(parsingText.getAlphabet(), "AAAAaaaaaaBBBbbbbCCCCCCccc");

			parsingText = new ParsingText(failText);
			Assert.assertEquals(parsingText.getAlphabet(), "");

			// aaaaAcAAaCcccCCcbBcaBcBbAbbB
			// AAAAaaaaaaBBBBbbbbCCCccccccc
			parsingText = new ParsingText(onlyAlphabet);
			Assert.assertEquals(parsingText.getAlphabet(), "AAAAaaaaaaBBBBbbbbCCCccccccc");

			parsingText = new ParsingText(onlyNumber);
			Assert.assertEquals(parsingText.getAlphabet(), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getNumber 테스트
	 * case 1 : 크롤링 데이터가 비었을 때
	 * case 2 : 크롤링 데이터가 정상일 때
	 * case 3 : 크롤링 데이터가 있으나 영문,숫자가 없을 때
	 * case 4 : 크롤링 데이터가 영문만 있을 때
	 * case 5 : 크롤링 데이터가 숫자만 있을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void setNumberTest() {
		try {
			parsingText = new ParsingText(emptyText);
			Assert.assertEquals(parsingText.getNumber(), "");

			// aaaAAAAb111bbBBBbCC2222cccCCa1010aaCC
			// 00111112222
			parsingText = new ParsingText(successText);
			Assert.assertEquals(parsingText.getNumber(), "00111112222");

			parsingText = new ParsingText(failText);
			Assert.assertEquals(parsingText.getNumber(), "");

			parsingText = new ParsingText(onlyAlphabet);
			Assert.assertEquals(parsingText.getNumber(), "");

			// 102937049112333445
			// 001112233334445799
			parsingText = new ParsingText(onlyNumber);
			Assert.assertEquals(parsingText.getNumber(), "001112233334445799");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getParsingText 테스트
	 * case 1 : 크롤링 데이터가 비었을 때
	 * case 2 : 크롤링 데이터가 정상일 때
	 * case 3 : 크롤링 데이터가 있으나 영문,숫자가 없을 때
	 * case 4 : 크롤링 데이터가 영문만 있을 때
	 * case 5 : 크롤링 데이터가 숫자만 있을 때
	 *
	 * @throws Exception
	 */
	@Test
	public void getParsingTextTest() {
		try {
			parsingText = new ParsingText(emptyText);
			Assert.assertEquals(parsingText.getParsingText(), "");

			// aaaAAAAb111bbBBBbCC2222cccCCa1010aaCC
			// AAAAaaaaaaBBBbbbbCCCCCCccc
			// 00111112222
			// A0A0A1A1a1a1a1a2a2a2B2BBbbbbCCCCCCccc
			parsingText = new ParsingText(successText);
			Assert.assertEquals(parsingText.getParsingText(), "A0A0A1A1a1a1a1a2a2a2B2BBbbbbCCCCCCccc");

			parsingText = new ParsingText(failText);
			Assert.assertEquals(parsingText.getParsingText(), "");

			parsingText = new ParsingText(onlyAlphabet);
			Assert.assertEquals(parsingText.getParsingText(), parsingText.getAlphabet());

			parsingText = new ParsingText(onlyNumber);
			Assert.assertEquals(parsingText.getParsingText(), parsingText.getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
