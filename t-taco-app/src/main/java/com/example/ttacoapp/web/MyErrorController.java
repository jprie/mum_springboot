package com.example.ttacoapp.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/error")
    public String showErrorPage(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        var status_code = Integer.valueOf(status.toString());

        return "error2";
    }
}
