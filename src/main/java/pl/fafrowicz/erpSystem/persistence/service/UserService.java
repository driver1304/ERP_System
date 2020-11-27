package pl.fafrowicz.erpSystem.persistence.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fafrowicz.erpSystem.error.UserAlreadyExistException;
import pl.fafrowicz.erpSystem.persistence.dao.RoleRepository;
import pl.fafrowicz.erpSystem.persistence.dao.UserRepository;
import pl.fafrowicz.erpSystem.persistence.entity.Company;
import pl.fafrowicz.erpSystem.persistence.entity.Role;
import pl.fafrowicz.erpSystem.persistence.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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


    public List<User> findAllEmployees(Company company, Role role) {
        return userRepository.findAllEmployees(company, role);
    }

    public Optional<User> findUserById(long userId) {
        return userRepository.findById(userId);
    }


    public void deleteById(long userId) {
        User user = userRepository.getOne(userId);
        user.setCompany(null);
        userRepository.save(user);
        userRepository.deleteById(userId);
    }

    public void editEmployeeAccount(long userId, User userToEdit) {
        User user = userRepository.getOne(userId);
        user.setFirstName(userToEdit.getFirstName());
        user.setLastName(userToEdit.getLastName());
        user.setEmail(userToEdit.getEmail());
        userRepository.save(user);
    }
}
