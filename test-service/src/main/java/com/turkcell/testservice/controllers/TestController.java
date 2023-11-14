package com.turkcell.testservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    @GetMapping("")
    public String getMapping() {
        return "GetMapping calisti.";
    }
    @PostMapping("")
    public String postMapping() {
        return "PostMapping calisti.";
    }
}
