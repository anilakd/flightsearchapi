package com.anilakdemir.flightsearchapi.security;

import com.anilakdemir.flightsearchapi.entity.User;
import com.anilakdemir.flightsearchapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author anilakdemir
 */
@Service
@Primary
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usrUser = userService.findByUsername(username);

        return JwtUserDetails.create(usrUser);
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
