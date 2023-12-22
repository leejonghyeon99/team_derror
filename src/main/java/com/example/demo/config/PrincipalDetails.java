package com.example.demo.config;

import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.Member;
import com.example.demo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private Long id;
    private Member member;

    public Member getMember() {
        return member;
    }


    public PrincipalDetails(Member member){

        this.member = member;
    }
    public PrincipalDetails(Long id){
        this.id = id;
    }

    public Long getId(){
        return member.getId();
    }
    public PrincipalDetails(Member member, Map<String, Object> attributes){
        this.attributes = attributes;
        this.member = member;
    }




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



    private Map<String, Object> attributes;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}

