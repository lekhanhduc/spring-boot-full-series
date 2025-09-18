package vn.khanhduc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Rest Controller = Controller + ResponseBody

    @GetMapping("/index")
    public String index() {
        return "Hello Backend Java";
    }

}
