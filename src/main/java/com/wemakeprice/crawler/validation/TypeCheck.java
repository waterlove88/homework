package com.wemakeprice.crawler.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = TypeCheckValidator.class)
public @interface TypeCheck {
	String message() default "타입 입력 값을 확인해주세요.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
