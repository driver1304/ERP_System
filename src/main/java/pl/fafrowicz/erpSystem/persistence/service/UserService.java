package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.dao.UserRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    CompanyService companyService;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, CompanyService companyService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.companyService = companyService;
        this.passwordEncoder = passwordEncoder;
    }


    public User registerNewAdminAccount(User newAccount) throws UserAlreadyExistException {
        if (emailExist(newAccount.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + newAccount.getEmail());
        }
        final User user = new User();
        user.setFirstName(newAccount.getFirstName());
        user.setLastName(newAccount.getLastName());
        user.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        user.setEmail(newAccount.getEmail());
        user.setCompany(companyService.registerNewCompany(newAccount.getCompany()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        return userRepository.save(user);

    }


    public User registerNewEmployeeAcount(User newAccount, Company company) throws UserAlreadyExistException {
        if (emailExist(newAccount.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + newAccount.getEmail());
        }
        final User user = new User();
        user.setFirstName(newAccount.getFirstName());
        user.setLastName(newAccount.getLastName());
        user.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        user.setEmail(newAccount.getEmail());
        user.setCompany(company);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }


    public List<User> findAllEmployees(Company company) {
        return userRepository.findAllEmployees(company);
    }

    public Optional<User> findUserByIdAndCompany(long userId, Company company) {
        return userRepository.findByIdAndAndCompany(userId, company);
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    public void deleteById(long userId, Company company) {
        Optional<User> optionalUser = userRepository.findByIdAndAndCompany(userId, company);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setCompany(null);
            userRepository.save(user);
            userRepository.deleteById(userId);
        }
    }

    public void editEmployeeAccount(long userId, User userToEdit, Company company) throws UserAlreadyExistException {
        Optional<User> optionalUser = userRepository.findByIdAndAndCompany(userId, company);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (!user.getEmail().equals(userToEdit.getEmail())) {
                if (emailExist(userToEdit.getEmail())) {
                    throw new UserAlreadyExistException("There is an account with that email address: " + userToEdit.getEmail());
                }
            }
            user.setFirstName(userToEdit.getFirstName());
            user.setLastName(userToEdit.getLastName());
            user.setEmail(userToEdit.getEmail());
            userRepository.save(user);
        }

    }


    public List<User> findAllForTask(long taskId, Company company) {
        return userRepository.findAllByTaskAndCompany(taskId, company);
    }

    public List<User> findAllWithoutTask(long taskId, Company company) {
        List<User> allEmployees = this.findAllEmployees(company);
        List<User> allForTask = findAllForTask(taskId, company);
        return allEmployees.stream()
                .filter(u -> !allForTask.contains(u))
                .collect(Collectors.toList());
    }
}
