package pl.fafrowicz.erpSystem.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyDto {
    private long id;
    @NotBlank
    private String name;

}
