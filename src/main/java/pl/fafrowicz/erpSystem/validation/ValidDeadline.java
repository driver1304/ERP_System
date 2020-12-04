package pl.fafrowicz.erpSystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DeadlineValidator.class)
@Documented
public @interface ValidDeadline {
    String message() default "The 'deadline' must be later than the 'start date'!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}