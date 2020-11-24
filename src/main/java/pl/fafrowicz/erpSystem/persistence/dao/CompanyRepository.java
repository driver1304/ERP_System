package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);
}
