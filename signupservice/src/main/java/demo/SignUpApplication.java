package demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注册信息的启动app
 * Created by JJH on 2017/4/13.
 */
@SpringBootApplication
@MapperScan("demo.repository")
public class SignUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(SignUpApplication.class,args);
    }
}
