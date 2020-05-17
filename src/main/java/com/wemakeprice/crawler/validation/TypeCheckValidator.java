package com.wemakeprice.crawler.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeCheckValidator implements ConstraintValidator<TypeCheck, String> {

	@Override
	public void initialize(TypeCheck constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return false;
		} else if (!(value.equals("excludeTag") || value.equals("allText"))) {
			return false;
		}

		return true;
	}

}
