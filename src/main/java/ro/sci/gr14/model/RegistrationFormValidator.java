package ro.sci.gr14.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.security.RegistrationForm;

@Component
public class RegistrationFormValidator implements Validator {
    @Autowired
    private IBaseUserRepository userRepo;

    @Override
    public boolean supports(Class aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm user = (RegistrationForm) o;

        if (userRepo.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.RegiterForm.username");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.RegiterForm.confirmPassword");
        }
    }
}
