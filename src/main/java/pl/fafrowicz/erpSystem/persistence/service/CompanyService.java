package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.persistence.dao.CompanyRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.web.dto.CompanyDto;

@Service
@Transactional
public class CompanyService {

    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company registerNewCompany(CompanyDto companyDto) {
        final Company company = new Company();
        company.setName(companyDto.getName());
        return companyRepository.save(company);

    }
}
