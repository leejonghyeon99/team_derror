package com.example.demo.config;

import com.example.demo.domain.Member;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = userService.findUsername(username);

        if(member != null){
            PrincipalDetails userDetails = new PrincipalDetails(member);
            userDetails.setUserService(userService);
            return userDetails;
        }

        throw new UsernameNotFoundException(username);

    }
}