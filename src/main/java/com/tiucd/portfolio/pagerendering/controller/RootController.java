package com.tiucd.portfolio.pagerendering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/api/page/view/landing-home";
    }
}