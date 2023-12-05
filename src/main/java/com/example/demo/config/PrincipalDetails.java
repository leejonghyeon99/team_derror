package com.example.demo.config;

import com.example.demo.domain.Authority;
import com.example.demo.domain.Member;
import com.example.demo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalDetails implements UserDetails{

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private Member member;

    public Member getMember() {
        return member;
    }


    public PrincipalDetails(Member member){

        this.member = member;
    }


//    public PrincipalDetails(Member member, Map<String, Object> attributes){
//        this.member = member;
//        this.attributes = attributes;
//    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        Collection<GrantedAuthority> collect = new ArrayList<>();


        List<Authority> list = userService.selectAuthoritiesById(member.getId());

        for(Authority auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getName();
                }

                @Override
                public String toString() {
                    return auth.getName();
                }
            });
        }

        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }



//    private Map<String, Object> attributes;
//
//    @Override
//    public String getName() {
//        return null;   // 사용하지 않을 예정
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;  // 어디서 받아올까?  -> 생성자!
//    }
}

