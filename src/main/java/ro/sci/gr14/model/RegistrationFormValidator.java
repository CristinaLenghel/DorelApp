package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.security.RegistrationForm;

/**
 * An implementation class of {@Validator} public interface to validate various String properties of an instance
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Component
public class RegistrationFormValidator implements Validator {
    @Autowired
    private IBaseUserRepository userRepo;

    @Override
    public boolean supports(Class aClass){
        return RegistrationForm.class.equals(aClass);
    }

    /**
     * Validates that the username of an instance is not empty and password confirmation equals password
     *
     * @param o      the object to be validated
     * @param errors the errors encountered
     */
    @Override
    public void validate(Object o, Errors errors){
        RegistrationForm user = (RegistrationForm) o;

        if (userRepo.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.RegiterForm.username");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.RegiterForm.confirmPassword");
        }
    }
}
