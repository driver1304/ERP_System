package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
