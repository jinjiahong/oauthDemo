package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 * Created by JJH on 2017/4/11.
 */
@RestController
public class jjhController {
    @RequestMapping("/jjh/zhi")
    public String zhi(){
        return "我是支付,受保护";
    }

    @RequestMapping("/jjh/query")
    public String query(){
        return "我是查询，受保护";
    }
}
