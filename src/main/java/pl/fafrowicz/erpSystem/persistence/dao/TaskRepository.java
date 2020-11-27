package pl.fafrowicz.erpSystem.persistence.dao;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByName(String name);



    @Query("select t FROM Task t JOIN FETCH t.employeesAndHoursBudgetList ahb WHERE ahb.user = ?1 AND t.completed=false ")
    List<Task> findAllActiveByUser(User user);

    @Query("select t FROM Task t JOIN FETCH t.employeesAndHoursBudgetList ahb WHERE ahb.user = ?1 AND t.completed=true ")
    List<Task> findAllCompletedByUser(User user);

    @Override
    void delete(Task task);




}
