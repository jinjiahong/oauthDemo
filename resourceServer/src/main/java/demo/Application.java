package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 资源服务器的app
 * Created by JJH on 2017/4/11.
 */
@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
