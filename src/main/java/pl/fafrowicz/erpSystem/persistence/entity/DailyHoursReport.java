package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter

@Entity
@Table(name = "dailyHoursReports")
public class DailyHoursReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;

    private LocalDate workingDate;

    private short workingTime;

}
