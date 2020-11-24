package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "user_task_hoursBudget")
public class EmployeeHoursBudgetForTask {
    @EmbeddedId
    EmployeeHoursBudgetForTaskKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    Task task;

    private short hoursBudget;

}
