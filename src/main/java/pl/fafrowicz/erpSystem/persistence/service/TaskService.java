package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.error.TaskAlreadyExistException;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.TaskRepository;
import pl.fafrowicz.erpSystem.persistence.dao.UserTaskHoursBudgetRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.Task;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.entity.UserTaskHoursBudget;
import pl.fafrowicz.erpSystem.web.dto.TaskDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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


    public List<Task> findAllActiveTasks(Company company) {
        return taskRepository.findAllActiveByCompanyAndCompletedFalse(company);
    }

    public List<Task> findAllCompletedTasks(Company company) {
        return taskRepository.findAllCompletedByCompanyAndCompletedTrue(company);
    }


    public Task addNewTask(Task newTask, Company company) throws TaskAlreadyExistException {
        if (nameExist(newTask.getName())) {
            throw new UserAlreadyExistException("There is an task with that name: " + newTask.getName());
        }
        newTask.setCompany(company);
        return taskRepository.save(newTask);
    }

    public void editTask(long taskId, Task newTask, Company company) throws TaskAlreadyExistException {
        Optional<Task> optionalTask = taskRepository.findByIdAndCompany(taskId, company);
        if (optionalTask.isPresent()) {
            Task oldTask = optionalTask.get();
            if (!oldTask.getName().equals(newTask.getName())) {
                if (nameExist(newTask.getName())) {
                    throw new UserAlreadyExistException("There is an task with that name: " + newTask.getName());
                }
                oldTask.setName(newTask.getName());
            }
            oldTask.setHoursBudget(newTask.getHoursBudget());
            oldTask.setDescription(newTask.getDescription());
            oldTask.setStartTerm(newTask.getStartTerm());
            oldTask.setDeadline(newTask.getDeadline());
            taskRepository.save(oldTask);
        }
    }

    public void deleteById(long taskId, Company company) {
        Optional<Task> optionalTask = taskRepository.findByIdAndCompany(taskId, company);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompany(null);
            taskRepository.save(task);
            taskRepository.deleteById(taskId);
        }
    }


    private boolean nameExist(String name) {
        return taskRepository.findByName(name) != null;
    }

    public Optional<Task> findByIdAndCompany(long taskId, Company company) {
        return taskRepository.findByIdAndCompany(taskId, company);
    }


    public void moveToCompleted(long taskId, Company company) {
        Optional<Task> optionalTask = taskRepository.findByIdAndCompany(taskId, company);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            taskRepository.save(task);
        }
    }

    public void moveToActive(long taskId, Company company) {
        Optional<Task> optionalTask = taskRepository.findByIdAndCompany(taskId, company);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(false);
            taskRepository.save(task);
        }
    }

    public List<Task> findTopThreeEarliestDeadlineForUserId(long userId) {
        return taskRepository.findTopThreeEarliestDeadlineForUserId(userId, PageRequest.of(0, 3));
    }
}
