package com.example.qtacoapp.register;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public String showRegistrationPage() {

        return "register";
    }

    @ModelAttribute("userForm")
    public UserForm user() {
        return new UserForm();
    }

    @PostMapping //Content-Type 'application/x-www-form-urlencoded;charset=UTF-8'
    public String processRegistration(@Valid UserForm userForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
           for (var error : errors.getFieldErrors()) {
               log.error("Validating user form: {}", error);
           }
           log.error("All errors: {}", errors.getAllErrors());
           return null;
        }
        log.info("Registering user: {}", userForm);
        model.addAttribute("username", userForm.getUsername());
        return "register_success";
    }



}
