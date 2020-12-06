package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u join fetch  u.roles r where u.company = ?1 and r.name = 'ROLE_USER'")
    List<User> findAllEmployees(Company company);


    Optional<User> findByIdAndAndCompany(long id, Company company);

    void deleteById(long userId);

    @Query("select u FROM User u JOIN FETCH u.hoursBudgetForTasksList hbft WHERE hbft.task.id = ?1 AND hbft.task.company= ?2")
    List<User> findAllByTaskAndCompany(long taskId, Company company);

}
