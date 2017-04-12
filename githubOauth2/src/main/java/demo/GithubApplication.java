package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 启动类
 * Created by JJH on 2017/4/6.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableOAuth2Client
@EnableAuthorizationServer
@EnableWebSecurity  //这个注释必须，加载HtmlSecurityConfig配置类
@EnableResourceServer
public class GithubApplication {
    public static void main(String[] args) {
        SpringApplication.run(GithubApplication.class,args);
    }
}
