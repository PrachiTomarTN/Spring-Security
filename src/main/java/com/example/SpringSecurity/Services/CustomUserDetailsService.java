package com.example.SpringSecurity.Services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.equals("Prachi Tomar"))
        {
            return new User("Prachi Tomar","Prachi123",new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not Found !!");
        }

    }
}
