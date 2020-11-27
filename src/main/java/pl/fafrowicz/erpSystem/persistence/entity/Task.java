package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    Set<UserTaskHoursBudget> employeesAndHoursBudgetList;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    Set<DailyHoursReport> dailyHoursReportsList;


    private String description;
    private LocalDate startTerm;
    private LocalDate deadline;
    private short hoursBudget;
    private boolean completed;

}
