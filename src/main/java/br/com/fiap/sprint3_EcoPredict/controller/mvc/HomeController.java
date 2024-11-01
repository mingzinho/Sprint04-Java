package br.com.fiap.sprint3_EcoPredict.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String home() {
        return "home"; // Nome da página inicial (home.html)
    }
}
