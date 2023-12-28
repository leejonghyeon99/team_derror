package com.example.demo.domain.user;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

            Member member = (Member) target;
            String username = member.getUsername();
            String email = member.getEmail();

            if (username == null || username.trim().isEmpty()) {
                ;
                errors.rejectValue("username", "아이디는 필수입니다");
            } else if (userService.exist(username)) {

                errors.rejectValue("username", "이미 존재하는 아이디 입니다");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "비밀번호를 입력하세요");

            if (!member.getPassword().equals(member.getRe_password())) {
                errors.rejectValue("re_password", "비밀번호와 비밀번호 재입력이 일치하지 않습니다.");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "이름은 필수 입력 항목입니다.");

            if (!member.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                errors.rejectValue("email", "유효한 이메일 주소를 입력하세요.");
            }

            if (member.getAge() <= 0) {
                errors.rejectValue("age", "나이는 0보다 커야 합니다.");
            }

            if (!member.getPhone().matches("010\\d{4}\\d{4}")) {
                errors.rejectValue("phone", "유효한 전화번호를 입력하세요.");
            }
    }

}