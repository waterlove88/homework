package com.wemakeprice.crawler.validation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TypeCheckValidatorTest {

	private static TypeCheckValidator typeCheckValidator;

	@BeforeClass
	public static void init() {
		typeCheckValidator = new TypeCheckValidator();
	}

	/**
	 * isValid 테스트
	 * case 1 : excludeTag (정상)
	 * case 2 : allText (정상)
	 * case 3 : ""
	 * case 4 : null
	 * case 5 : "" 과 null 아닌 다른 값
	 *
	 * @throws Exception
	 */
	@Test
	public void typeCheckValidatorTest() {
		Assert.assertTrue(typeCheckValidator.isValid("excludeTag", null));
		Assert.assertTrue(typeCheckValidator.isValid("allText", null));
		Assert.assertFalse(typeCheckValidator.isValid("", null));
		Assert.assertFalse(typeCheckValidator.isValid(null, null));
		Assert.assertFalse(typeCheckValidator.isValid("4125564", null));
	}
}
