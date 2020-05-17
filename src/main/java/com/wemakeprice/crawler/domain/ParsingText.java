package com.wemakeprice.crawler.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 데이터를 알파벳과 숫자로 파싱하는 모델 클래스
 * <p>
 * alphabet : 영문 추출 데이터
 * number : 숫자 추출 데이터
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
@Getter
public class ParsingText {
	private String alphabet;
	private String number;

	/**
	 * 크롤링 한 데이터를 받아 알파벳과 숫자를 추출
	 *
	 * @param text 크롤링 데이터
	 */
	public ParsingText(String text) {
		this.alphabet = setAlphabet(text);
		this.number = setNumber(text);
	}

	/**
	 * 알파벳과 숫자를 추출한 데이터를 교차로 출력
	 * 교차 출력 : 알파벳, 숫자, 알파벳, 숫자, ...
	 *
	 * @return String 교차출력된 텍스트
	 */
	public String getParsingText() {

		// 추출된 알파벳, 숫자가 모두 없으면 데이터 없음
		if (alphabet.length() == 0 && number.length() == 0) {
			return "";

			// 추출된 알파벳만 있고 숫자가 없으면 숫자만 리턴
		} else if (alphabet.length() == 0) {
			return number;

			// 추출된 숫자만 있고 알파벳이 없으면 알파벳만 리턴
		} else if (number.length() == 0) {
			return alphabet;
		}

		// 두 데이터 중 작은 기준으로 교차출력 후 더 긴값을 끝에 붙음
		int min = Math.min(alphabet.length(), number.length());

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < min; i++) {
			stringBuilder.append(alphabet.charAt(i)).append(number.charAt(i));
		}

		if (alphabet.length() == number.length()) {
			return stringBuilder.toString();
		} else if (alphabet.length() == min) {
			return stringBuilder.append(number.substring(min)).toString();
		} else if (number.length() == min) {
			return stringBuilder.append(alphabet.substring(min)).toString();
		} else {
			return "";
		}
	}

	/**
	 * 크롤링 한 데이터를 받아 알파벳을 추출 후 오름차순 정렬
	 * 오름차순 : 알파벳 사전순 + 대문자 우선
	 * ex) AaABbBCbC : AAaBBbCC
	 *
	 * @param text
	 * @return
	 */
	public String setAlphabet(String text) {

		// 데이터가 없으면 빈 값 리턴
		if (StringUtils.isEmpty(text)) {
			return "";
		}

		// 패턴에 따라 알파벳만 추출
		String alphabetPattern = "[^a-zA-Z]+";
		String[] alphabetTextArray = text.replaceAll(alphabetPattern, "").split("");

		// String.CASE_INSENSITIVE_ORDER 를 변형
		// 두 개를 대문자로 변경하여 비교 후 같으면 대문자가 앞으로
		// 두 개를 대문자로 변경 후 같지 않으면 소문자 변경 후 사전 순서대로
		Arrays.sort(alphabetTextArray, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				char c1 = s1.charAt(0);
				char c2 = s2.charAt(0);
				if (Character.toUpperCase(c1) != Character.toUpperCase(c2)) {
					return Character.toLowerCase(c1) - Character.toLowerCase(c2);
				} else {
					return c1 - c2;
				}
			}
		});

		return String.join("", alphabetTextArray);
	}

	/**
	 * 크롤링 한 데이터를 받아 숫자를 추출 후 오름차순 정렬
	 *
	 * @param text
	 * @return
	 */
	public String setNumber(String text) {

		// 데이터가 없으면 빈 값 리턴
		if (StringUtils.isEmpty(text)) {
			return "";
		}

		// 패턴에 따라 숫자만 추출
		String numberPattern = "[^0-9]+";
		String[] numberTextArray = text.replaceAll(numberPattern, "").split("");

		// 정렬
		Arrays.sort(numberTextArray);

		return String.join("", numberTextArray);
	}
}
