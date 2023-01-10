package com.test.spring.service;

import com.test.spring.CustomUserDetails;
import com.test.spring.entity.UserEntity;
import com.test.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OneTimePasswordService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new CustomUserDetails(userEntity);
    }
}
