package pl.fafrowicz.erpSystem.web.dto;

import lombok.Getter;
import lombok.Setter;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.validation.PasswordMatches;
import pl.fafrowicz.erpSystem.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    private long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String password;
    private String matchingPassword;


    @ValidEmail
    private String email;


    @NotNull
    private CompanyDto company;


    public void setNameFromCompany(Company companyObj) {
        this.company.setName(companyObj.getName());
    }




}
