package com.challenger.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DocApi {
    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException{
        response.sendRedirect("swagger-ui.html");
    }
}
