package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByName(String name);


    @Query("SELECT t FROM Task t JOIN FETCH t.employeesAndHoursBudgetList ahb WHERE ahb.user = ?1 AND t.completed=false ")
    List<Task> findAllActiveByUser(User user);

    @Query("SELECT t FROM Task t JOIN FETCH t.employeesAndHoursBudgetList ahb WHERE ahb.user = ?1 AND t.completed=true ")
    List<Task> findAllCompletedByUser(User user);


    @Query("SELECT t FROM Task t JOIN FETCH t.employeesAndHoursBudgetList ahb WHERE ahb.user.id = ?1 AND t.completed=false ORDER BY t.deadline ASC ")
    List<Task> findTopThreeEarliestDeadlineForUserId(long userId, Pageable pegeable);


    @Override
    void delete(Task task);


    List<Task> findAllActiveByCompanyAndCompletedFalse(Company company);

    List<Task> findAllCompletedByCompanyAndCompletedTrue(Company company);

    Optional<Task> findByIdAndCompany(long taskId, Company company);

}
