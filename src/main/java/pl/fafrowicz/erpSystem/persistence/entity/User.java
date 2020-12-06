package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import pl.fafrowicz.erpSystem.validation.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Getter
@Setter

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(max = 40)
    private String firstName;
    @NotBlank
    @Size(max = 40)
    private String lastName;

    @ManyToOne
    private Company company;


    @ValidEmail
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<UserTaskHoursBudget> hoursBudgetForTasksList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<DailyHoursReport> dailyHoursReportsList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}
