package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.Role;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.awt.print.Book;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u where u.company = ?1 and ?2 member of u.roles")
    List<User> findAllEmployees (Company company, Role role);


    @Override
    void delete(User user);

}
