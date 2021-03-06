package mx.net.nhtzr.base.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "forward:/hello/world";
    }

    @RequestMapping("/hello/{name}")
    public ModelAndView hello(@PathVariable String name) {
        return new ModelAndView("index", "name", name);
    }

}
