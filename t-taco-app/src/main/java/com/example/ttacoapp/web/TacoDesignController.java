package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class TacoDesignController {

    @ModelAttribute("taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/design")
    public String showDesignForm() {

        return "designForm";
    }
}
