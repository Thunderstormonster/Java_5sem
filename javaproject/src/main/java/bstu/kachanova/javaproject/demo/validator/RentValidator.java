package bstu.kachanova.javaproject.demo.validator;

import bstu.kachanova.javaproject.demo.models.RentForm;
import bstu.kachanova.javaproject.demo.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RentForm computerStuff =(RentForm)o;
        if(computerStuff.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}
