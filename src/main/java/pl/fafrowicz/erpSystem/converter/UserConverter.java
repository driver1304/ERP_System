package pl.fafrowicz.erpSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.fafrowicz.erpSystem.persistence.entity.User;
import pl.fafrowicz.erpSystem.persistence.service.UserService;

public class UserConverter implements Converter<String, User> {
    @Autowired
    UserService userService;

    @Override
    public User convert(String userId) {
        return userService.findById(Long.parseLong(userId)).get();
    }
}
