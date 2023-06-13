package ru.maxima.school.projectmaximaedo.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maxima.school.projectmaximaedo.model.User;
import ru.maxima.school.projectmaximaedo.service.PersonDetailsService;

@Component
public class PersonValidator  implements Validator {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            personDetailsService.loadUserByUsername(user.getFirstName());
        } catch (UsernameNotFoundException e) {
            return;
        }

        errors.rejectValue("name", "Пользователь с таким именем не найден");
    }
}
