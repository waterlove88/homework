package com.wemakeprice.crawler.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 요청 type을 체크하는 validator
 * type : excludeTag(태그제외), allText(모든 텍스트)
 *
 * @author waterlove88@gmail.com
 * @since 2020.05.17
 */
public class TypeCheckValidator implements ConstraintValidator<TypeCheck, String> {

	@Override
	public void initialize(TypeCheck constraintAnnotation) {

	}

	/**
	 * 요청 type 체크
	 * 필수 값이며 excludeTag 또는 allText
	 * 실패를 가정하고 맞는 상황일 때만 성공 리턴
	 *
	 * @param value
	 * @param context
	 * @return
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!StringUtils.isEmpty(value) && (value.equals("excludeTag") || value.equals("allText"))) {
			return true;
		}

		return false;
	}

}
