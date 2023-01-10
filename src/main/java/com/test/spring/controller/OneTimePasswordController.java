package com.test.spring.controller;

import com.test.spring.entity.UserEntity;
import com.test.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OneTimePasswordController {

    @Autowired
    private UserRepo userRepo;

    public OneTimePasswordController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String openHomePage() {
        return "index";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {

        List<UserEntity> userEntityList = userRepo.findAll();
        model.addAttribute("userEntityList", userEntityList);
        return "users_list";
    }

    @GetMapping("/register")
    public String openRegisterForm(Model model){
        model.addAttribute("entity", new UserEntity());
        return "sign_up";

    }

    @PostMapping("/register_form")
    public String processRegister(UserEntity userEntity) {
        UserEntity existUser = userRepo.findByEmail(userEntity.getEmail());
        if (existUser != null) {
            return "The user found in database";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);

        userRepo.save(userEntity);

        return "success";
    }
}
