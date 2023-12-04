//package com.example.demo.domain;
//
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//public class MemberValidator implements Validator {
//    @Autowired
//    UserService userService;
//
//    @Override
//    public boolean supports(Class<?> cl){
//        boolean result = Member.class.isAssignableFrom(cl);
//        return result;
//    }
//
//    @Override
//    public void validate(Object user, Errors errors){
//        Member member = (Member)user;
//
//        String username = ((Member) user).getUsername();
//        if(username==null || username.trim().isEmpty()){
//            errors.rejectValue("username","username은 필수입니다.");
//        }
//    }
//}
