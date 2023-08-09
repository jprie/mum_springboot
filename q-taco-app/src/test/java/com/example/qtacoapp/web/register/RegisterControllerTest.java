package com.example.qtacoapp.web.register;


import com.example.qtacoapp.web.register.UserForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_process_registration_successful() throws Exception {

        var userForm = new UserForm("abcd", "1aPaula,34", "1aPaula,34");
        mockMvc.perform(
                        post("/register?username=$1")
                                .param("username", userForm.getUsername())
                                .param("password", userForm.getPassword())
                                .param("confirmPassword", userForm.getConfirmPassword()
                                )
                )
                .andExpect(status().isOk())
                .andExpect(view().name("register_success"))
                .andExpect(content().string(containsString("Registered successfully")));
    }

    @Test
    void test_process_registration_passwords_do_not_match() throws Exception {

        var userForm = new UserForm("abcd", "1aPaula,34", "1aPaula,3");
        mockMvc.perform(
                        post("/register?username=$1")
                                .param("username", userForm.getUsername())
                                .param("password", userForm.getPassword())
                                .param("confirmPassword", userForm.getConfirmPassword())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(content().string(containsString("passwords do not match")));
    }
}
