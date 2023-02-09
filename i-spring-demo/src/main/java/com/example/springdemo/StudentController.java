package com.example.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String getStudentInfo(Model model) {

        Student student = new Student("Johannes", "Priebsch");
        System.out.println("In getStudentInfo");

        // stellt der Template engine die Objekte aus Java zur Verfügung
        model.addAttribute("student", student);

        return "student.html";
    }
}
