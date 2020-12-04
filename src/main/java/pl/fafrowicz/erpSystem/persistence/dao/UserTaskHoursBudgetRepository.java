package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;

import java.util.List;

public interface UserTaskHoursBudgetRepository extends JpaRepository<UserTaskHoursBudget, Long> {

    @Modifying
    void deleteByUserAndTask(User user, Task task);

    List<UserTaskHoursBudget> findAllByTask_Id(long taskId);

    @Query("SELECT SUM (uthb.hoursBudget) FROM UserTaskHoursBudget uthb WHERE uthb.task.id=?1")
    Integer sumAllowedHoursBudgetForTask(long taskId);

    UserTaskHoursBudget findByUser_IdAndTask_Id(long employeeId, long taskId);

    List<UserTaskHoursBudget> findAllByUser_Id(long userId);
}