package com.wemakeprice.crawler.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class ParsingText {
	private String alphabet;
	private String number;

	public ParsingText(String text) {
		this.alphabet = setAlphabet(text);
		this.number = setNumber(text);
	}

	public String getParsingText() {
		if (alphabet.length() == 0 && number.length() == 0) {
			return "";
		} else if (alphabet.length() == 0) {
			return number;
		} else if (number.length() == 0) {
			return alphabet;
		}

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

	public String setAlphabet(String text) {
		String alphabetPattern = "[^a-zA-Z]+";
		String[] alphabetTextArray = text.replaceAll(alphabetPattern, "").split("");

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

	public String setNumber(String text) {
		String numberPattern = "[^0-9]+";
		String[] numberTextArray = text.replaceAll(numberPattern, "").split("");

		Arrays.sort(numberTextArray);

		return String.join("", numberTextArray);
	}
}
