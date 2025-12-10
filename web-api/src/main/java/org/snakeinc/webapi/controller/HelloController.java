package org.snakeinc.webapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/hello")
class HelloController {
    @GetMapping
    public String HelloController() {
        return "Hello World";
    }
}
