package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import pl.fafrowicz.erpSystem.web.dto.TaskDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private LocalDate startTerm;
    @NotNull
    private LocalDate deadline;

    @NotNull
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
