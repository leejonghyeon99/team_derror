package com.example.demo.domain.board;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 @Author 장고운
 */
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        boolean result = Post.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validate() 호출");
        Post post = (Post) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title", "글제목은 필수입니다.");

    }
}
