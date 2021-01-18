package com.app.tracking.book.internal.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ISBNValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBNConstraint {

  String message() default "Invalid ISBN number";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  RequestType[] requestType() default {};
}
