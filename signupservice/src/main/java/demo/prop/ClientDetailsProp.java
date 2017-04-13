package demo.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注册的客户端固定信息配置
 * Created by JJH on 2017/4/13.
 */
@Data
@Component
@ConfigurationProperties("clientInfo")
public class ClientDetailsProp {
    private String authorizedGrantTypes;
    private String authorities;
    private int accessTokenValidity;
    private int refreshTokenValidity;
}
