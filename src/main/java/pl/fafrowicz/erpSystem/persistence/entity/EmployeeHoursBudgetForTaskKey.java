package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode

@Embeddable
public class EmployeeHoursBudgetForTaskKey implements Serializable {
    @Column(name = "user_id")
    long userId;

    @Column(name = "task_id")
    long taskId;
}
