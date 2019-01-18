package base.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/service/index.html")
    public String index() {
        return "index";
    }
}
