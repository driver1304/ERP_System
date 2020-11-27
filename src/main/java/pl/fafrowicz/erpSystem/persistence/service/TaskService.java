package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.persistence.dao.TaskRepository;
import pl.fafrowicz.erpSystem.persistence.dao.UserTaskHoursBudgetRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;

import java.util.List;

@Service
@Transactional
public class TaskService {

    TaskRepository taskRepository;
    UserTaskHoursBudgetRepository userTaskHoursBudgetRepository;

    public TaskService(TaskRepository taskRepository, UserTaskHoursBudgetRepository userTaskHoursBudgetRepository) {
        this.taskRepository = taskRepository;
        this.userTaskHoursBudgetRepository = userTaskHoursBudgetRepository;
    }


    public List<Task> findAllActiveForUser(User user) {
        return taskRepository.findAllActiveByUser(user);
    }

    public List<Task> findAllCompletedForUser(User user) {
        return taskRepository.findAllCompletedByUser(user);
    }




}
