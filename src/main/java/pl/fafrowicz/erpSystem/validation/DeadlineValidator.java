package pl.fafrowicz.erpSystem.validation;

import pl.fafrowicz.erpSystem.web.dto.TaskDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DeadlineValidator implements ConstraintValidator<ValidDeadline, Object> {
    public void initialize(ValidDeadline constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
       final TaskDto task = (TaskDto) obj;
       return LocalDate.parse(task.getStartTerm()).isBefore(LocalDate.parse(task.getDeadline()));
    }
}
