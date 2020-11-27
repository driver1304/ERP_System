package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.persistence.dao.CompanyRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;

@Service
@Transactional
public class CompanyService {

    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company registerNewCompany(Company companyDto) {
        final Company company = new Company();
        company.setName(companyDto.getName());
        return companyRepository.save(company);

    }
}
