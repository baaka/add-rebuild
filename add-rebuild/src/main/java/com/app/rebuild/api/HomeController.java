package com.app.rebuild.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("redirect:app/index.html");
    }

    @GetMapping("/app/appForm")
    public ModelAndView task() {
        return new ModelAndView("forward:index.html");
    }

    @GetMapping("/app/donation")
    public ModelAndView topUsers() {
        return new ModelAndView("forward:index.html");
    }

}
