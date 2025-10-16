package com.tiucd.portfolio.pagerendering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/api/page/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Dashboard loaded successfully");
        return "simple-dashboard";
    }
}