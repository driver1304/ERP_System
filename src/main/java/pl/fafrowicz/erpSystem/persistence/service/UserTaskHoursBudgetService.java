package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.persistence.dao.UserTaskHoursBudgetRepository;

@Service
@Transactional
public class UserTaskHoursBudgetService {

    UserTaskHoursBudgetRepository userTaskHoursBudgetRepository;

    @Autowired
    public UserTaskHoursBudgetService(UserTaskHoursBudgetRepository userTaskHoursBudgetRepository) {
        this.userTaskHoursBudgetRepository = userTaskHoursBudgetRepository;
    }

    public void delete(long userId, long taskId) {
        userTaskHoursBudgetRepository.deleteByUserIdAndTaskId(userId, taskId);
    }


}
