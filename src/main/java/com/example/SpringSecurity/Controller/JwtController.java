package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Helper.JwtUtil;
import com.example.SpringSecurity.Model.JwtRequest;
import com.example.SpringSecurity.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
   private CustomUserDetailsService customUserDetailsService;

    @Autowired
   private JwtUtil jwtUtil;

   @Autowired
   private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
            UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

          String token= this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
    }

