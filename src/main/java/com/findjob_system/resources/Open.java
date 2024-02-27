package com.findjob_system.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/findjob")
public class Open {
    @GetMapping("/")
    public String openWebsite() {
        return "website";
    }

}
