package pl.fafrowicz.erpSystem.web.dto;

import lombok.Getter;
import lombok.Setter;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.validation.ValidDeadline;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter


@ValidDeadline
public class TaskDto {
    private long id;
    @Size(max = 40)
    @NotBlank
    private String name;

    @Size(max = 300)
    private String description;

    @NotBlank
    private String startTerm;
    @NotBlank
    private String deadline;

    @NotNull
    private short hoursBudget;

    public Task ToTask() {
        Task task = new Task();
        task.setName(this.getName());
        task.setDescription(this.getDescription());
        task.setStartTerm(LocalDate.parse(this.getStartTerm()));
        task.setDeadline(LocalDate.parse(this.getDeadline()));
        task.setHoursBudget(this.getHoursBudget());
        return task;
    }
}
