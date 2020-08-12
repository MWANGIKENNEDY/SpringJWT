package com.springjwt.springjwt.controllers;

import com.springjwt.springjwt.models.UserModel;
import com.springjwt.springjwt.services.UserService;
import com.springjwt.springjwt.services.UserServiceImpl;
import com.springjwt.springjwt.util.AuthRequest;
import com.springjwt.springjwt.util.AuthResponse;
import com.springjwt.springjwt.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserModel userModel){
        userService.registerUser(userModel);
        return new ResponseEntity<>("User registered",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/home",method = RequestMethod.GET)
    public String homePage(){
        return "Hello Engineer";
    }

    //Authentication controller
    @RequestMapping(value = "/api/login",method = RequestMethod.POST)
    public ResponseEntity<Object> loginUser(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        }
        catch(BadCredentialsException e){
            throw new Exception("Wrong username or password",e);
        }

        final UserDetails userDetails=userDetailsService.loadUserByUsername(
            authRequest.getUsername());

        final String jwt=jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthResponse(jwt),HttpStatus.OK);  
        
    }


  
}