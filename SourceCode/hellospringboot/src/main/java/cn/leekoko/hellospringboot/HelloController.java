package cn.leekoko.hellospringboot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value="/say",method = RequestMethod.GET)
    public String say(@RequestParam("id") Integer id){
        return "Hello Spring Boot:"+id;
    }

}
