package demo.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jdbc连接mysql信息
 * Created by JJH on 2017/4/10.
 */
@Component
@ConfigurationProperties("jdbc")
@Data
public class JdbcContextConfig {

    private String driverClassName;
    private String url;
    private String user;
    private String pass;

}
