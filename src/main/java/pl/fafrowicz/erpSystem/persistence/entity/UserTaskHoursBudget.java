package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter

@Entity
@Table(name = "user_task_hoursBudget", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "task_id"}))
public class UserTaskHoursBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;

    @Range(min = 1, max = Short.MAX_VALUE)
    private short hoursBudget;

    private String description;


}
