package self.learning.backend.lib.mgmt.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryHomeController {
    @GetMapping({"/", "/library"})
    public String home() {
        return "home";
    }
}
