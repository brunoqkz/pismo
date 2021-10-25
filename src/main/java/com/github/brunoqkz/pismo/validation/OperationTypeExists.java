package com.github.brunoqkz.pismo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = OperationTypeExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationTypeExists {
  String message() default "Operation Type does not exists";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
