package pl.fafrowicz.erpSystem.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import pl.fafrowicz.erpSystem.web.dto.UserDto;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;

    @ManyToOne
    private Company company;

    private String email;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<EmployeeHoursBudgetForTask> hoursBudgetForTasksList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<DailyHoursReport> dailyHoursReportsList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    public UserDto castToDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setFirstName(this.firstName);
        userDto.setLastName(this.lastName);
        userDto.setEmail(this.email);
        userDto.setCompany(this.company.castToDto());
        return userDto;
    }


}
