package com.bsuir.validation;

import com.bsuir.dao.FowlDao;
import com.bsuir.dao.impl.FowlDaoImpl;
import com.bsuir.model.Fowl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 *  Класс для валидации данных формы добавления {@link Fowl} новой птицы.
 *  Значения вроде "fowlNumber.negativeValue" объявлены в файлах ValidationMessages
 */

@Component
public class FowlValidator implements Validator{

    @Autowired
    private FowlDao fowlDao;

    // Указываем, что данный валидатор работает только с классом Fowl
    @Override
    public boolean supports(Class<?> clazz) {
        return Fowl.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Fowl fowl = (Fowl) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");

        if(fowl.getNumber() < 0){
            errors.rejectValue("number", "fowlNumber.negativeValue");
        }

        for(char c : fowl.getName().toCharArray()){
            if(!Character.isAlphabetic(c)){
                errors.rejectValue("name", "fowlName.notAlphabetic");
                break;
            }
        }

        for(char c : fowl.getDescription().toCharArray()){
            if(!Character.isAlphabetic(c)){
                errors.rejectValue("description", "fowlName.notAlphabetic");
                break;
            }
        }

        List<Fowl> fowls = fowlDao.getAll();
        for(Fowl currentFowl : fowls){
            if(fowl.equals(currentFowl)){
                errors.rejectValue("name", "fowlName.alreadyExists");
            }
        }
    }
}
