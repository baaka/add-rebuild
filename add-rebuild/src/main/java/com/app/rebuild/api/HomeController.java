package com.app.rebuild.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("redirect:app/index.html");
    }

    @GetMapping("/app/appForm")
    public ModelAndView appForm() {
        return new ModelAndView("forward:index.html");
    }

    @GetMapping("/app/donation")
    public ModelAndView donation() {
        return new ModelAndView("forward:index.html");
    }

}
