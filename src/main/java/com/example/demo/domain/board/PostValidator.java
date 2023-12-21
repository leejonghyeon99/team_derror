package com.example.demo.domain.board;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author 장고운
 */
public class PostValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");

        boolean result = PostPage.class.isAssignableFrom(clazz) || Post.class.isAssignableFrom(clazz);

        // QryCommentList를 제외시킴
        // 디테일에서 에러나고 있음, 코드를 넣어도 제외가 안됨
        if (clazz.getSimpleName().equals("QryCommentList")) {
            result = false;
        }

        System.out.println("결과" + result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validate() 호출");
        PostPage post = (PostPage) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "글 제목은 필수입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "글 내용은 필수입니다.");

    }
}

