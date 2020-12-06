package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import pl.fafrowicz.erpSystem.web.dto.TaskDto;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotBlank
    @Size(max = 40)
    private String name;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<UserTaskHoursBudget> employeesAndHoursBudgetList;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    Set<DailyHoursReport> dailyHoursReportsList;

    @ManyToOne
    private Company company;
    @Size(max = 300)
    private String description;

    @NotNull
    @Future
    private LocalDate startTerm;

    @NotNull
    @Future
    private LocalDate deadline;

    @NotNull
    @Range(min = 1, max = Short.MAX_VALUE)
    private short hoursBudget;
    private boolean completed = false;


    public TaskDto toTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(this.getId());
        taskDto.setName(this.getName());
        taskDto.setDescription(this.getDescription());
        taskDto.setStartTerm(this.getStartTerm().toString());
        taskDto.setDeadline(this.getDeadline().toString());
        taskDto.setHoursBudget(this.getHoursBudget());
        return taskDto;
    }
}
