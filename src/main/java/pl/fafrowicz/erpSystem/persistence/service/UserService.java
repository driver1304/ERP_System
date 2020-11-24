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
import pl.fafrowicz.erpSystem.web.dto.UserDto;

import java.util.Arrays;
import java.util.List;

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

    public User registerNewAdminAccount(UserDto accountDto) throws UserAlreadyExistException {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setCompany(companyService.registerNewCompany(accountDto.getCompany()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        return userRepository.save(user);

    }


    public User registerNewEmployeeAcount(UserDto accountDto,Company company) throws UserAlreadyExistException {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
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


}
