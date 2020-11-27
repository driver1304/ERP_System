package pl.fafrowicz.erpSystem.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;

public interface UserTaskHoursBudgetRepository extends JpaRepository<UserTaskHoursBudget, Long> {

    @Modifying
    void deleteByUserIdAndTaskId(long userId, long taskId);
}