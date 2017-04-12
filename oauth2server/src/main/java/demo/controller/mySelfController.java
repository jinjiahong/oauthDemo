package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 测试的控制器
 * Created by JJH on 2017/4/7.
 */
@Controller
public class mySelfController {

    @ResponseBody
    @GetMapping("/jjh/first")
    public String first(){
        return "受保护的user";
    }

    @ResponseBody
    @RequestMapping("/jjh/second")
    public String jjh(){
        return "OAuth2S类的安全拦截无效";
    }

    @ResponseBody
    @RequestMapping("/")
    public String defautl(){
        return "hahah";
    }

    @RequestMapping("/login")
    public String login(){
        return "userLogin.html";
    }

    @ResponseBody
    @RequestMapping("/jjh")
    public String holdReciret(@RequestParam String code){
        return "得到的code为："+code;
    }

}
