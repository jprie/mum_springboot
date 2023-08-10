package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // erzeugt einen Logger
@Controller
public class HelloController {

    @ModelAttribute("userForm") // liefert Grundlage für die Form (Backing bean)
    public UserForm user() {
        return new UserForm();
    }

    @ModelAttribute
    public void fillPhonePrefixesInModel(Model model) {

        model.addAttribute("phonePrefixes", List.of("AT +43", "DE +49", "IT +39"));

    }

    @GetMapping("/hello") // /hello?name=<name>
    public String hello(
            @RequestParam(value = "name", required = false, defaultValue = "DefaultName") String name,
            Model model) {

        // Model: stellt der Template-Engine die Java-Objekte zur Verfügung
        model.addAttribute("name", name);

        return "helloPage";
    }


    @GetMapping("/hello/{name}")
    public String helloWithPathVariable(@PathVariable("name") String myName,
                                        Model model) {

        model.addAttribute("name", myName);

        return "helloPage";
    }

    @PostMapping("/hello")
    public String processName(UserForm userForm,
                              Model model) {

//        User user = new User(name, phonePrefix);
        log.info("Neuer user: {}", userForm);
        model.addAttribute("name", userForm);

        return "helloPage";
    }
}
