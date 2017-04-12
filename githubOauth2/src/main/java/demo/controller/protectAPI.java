package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 需要认证才能访问的API
 * Created by JJH on 2017/4/6.
 */
@RestController
public class protectAPI {

    @RequestMapping("/jjh/first")
    public String firstApi(){
        return "我是第一个受保护的api";
    }

    @RequestMapping("/jjh/second")
    public String secondAPI(){
        return "我是第二个受保护的api";
    }

    @RequestMapping("/")
    public String indexApi(Principal principal){
        if(null != principal){
            String info = principal.getName();
            return "当前登陆用户名称为："+info;
        }
        return "这是index页面，所有人都能访问";
    }

    @RequestMapping("/return")
    public String returnPage(@RequestParam String code){
        return "github授权返回"+code;
    }

}
