package com.springjwt.springjwt.config;

import com.springjwt.springjwt.models.UserModel;
import com.springjwt.springjwt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Get UserModel object from the database
        UserModel user=userRepo.getUser(username);
        if(user==null){
            throw new UsernameNotFoundException("User does not exist");
        
        }
        return new MyUserDetails(user);
    }

}
    
