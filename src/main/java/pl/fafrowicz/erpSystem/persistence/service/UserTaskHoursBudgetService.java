package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.error.TaskAlreadyExistException;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.UserTaskHoursBudgetRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserTaskHoursBudgetService {

    UserTaskHoursBudgetRepository userTaskHoursBudgetRepository;
    UserService userService;
    TaskService taskService;

    public UserTaskHoursBudgetService(UserTaskHoursBudgetRepository userTaskHoursBudgetRepository, UserService userService, TaskService taskService) {
        this.userTaskHoursBudgetRepository = userTaskHoursBudgetRepository;
        this.userService = userService;
        this.taskService = taskService;
    }

    public void delete(long userId, long taskId, Company company) {
        Optional<User> optionalUser = userService.findUserByIdAndCompany(userId, company);
        Optional<Task> optionalTask = taskService.findByIdAndCompany(taskId, company);
        if (optionalUser.isPresent() && optionalTask.isPresent()) {
            userTaskHoursBudgetRepository.deleteByUserAndTask(optionalUser.get(), optionalTask.get());
        }
    }

    public UserTaskHoursBudget save(UserTaskHoursBudget userTaskHoursBudget) {

        return userTaskHoursBudgetRepository.save(userTaskHoursBudget);
    }

    public List<UserTaskHoursBudget> findAllByTaskId(long taskId) {
        return userTaskHoursBudgetRepository.findAllByTask_Id(taskId);
    }

    public List<UserTaskHoursBudget> findAllByUserId(long userId) {
        return userTaskHoursBudgetRepository.findAllByUser_Id(userId);
    }

    public Map<Long, UserTaskHoursBudget> hoursBudgetPerUserForTask(long taskId) {
        return findAllByTaskId(taskId).stream()
                .collect(Collectors.toMap(element -> element.getUser().getId(), element -> element));
    }

    public Integer sumAllowedHoursBudgetForTask(long taskId) {
        Integer sum = 0;
        Integer result = userTaskHoursBudgetRepository.sumAllowedHoursBudgetForTask(taskId);
        if (result != null) {
            sum = result;
        }
        return sum;
    }

    public UserTaskHoursBudget findByUserAndTask(long employeeId, long taskId) {
        return userTaskHoursBudgetRepository.findByUser_IdAndTask_Id(employeeId, taskId);
    }

    public Map<Long, UserTaskHoursBudget> hoursBudgetPerTaskForUser(long userId) {
        return findAllByUserId(userId).stream()
                .collect(Collectors.toMap(element -> element.getTask().getId(), element->element));
    }
}
