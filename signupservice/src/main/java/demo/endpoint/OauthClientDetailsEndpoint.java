package demo.endpoint;

import demo.entity.OauthClientDetails;
import demo.prop.ClientDetailsProp;
import demo.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册client信息端口
 * Created by JJH on 2017/4/13.
 */
@RestController
@RequestMapping("/signUp")
public class OauthClientDetailsEndpoint {

    private OauthClientDetailsService oauthClientDetailsService;
    private ClientDetailsProp clientDetailsProp;

    @Autowired
    public OauthClientDetailsEndpoint(OauthClientDetailsService oauthClientDetailsService,ClientDetailsProp clientDetailsProp){
        this.oauthClientDetailsService = oauthClientDetailsService;
        this.clientDetailsProp = clientDetailsProp;
    }

    @PostMapping("/clientDetails")
    public String insertClientDetailsInfo(@RequestBody OauthClientDetails oauthClientDetails){
        //检查是否已经存在，存在则返回clientId已存在
        if(null == oauthClientDetails.getClientId()){
            return "参数出错，没有必要参数clientId";
        }
        boolean isExist = this.oauthClientDetailsService.isExist(oauthClientDetails.getClientId());
        if(isExist){
            return "clientId已存在！";
        }
        oauthClientDetails.setAuthorizedGrantTypes(this.clientDetailsProp.getAuthorizedGrantTypes());
        oauthClientDetails.setAuthorities(this.clientDetailsProp.getAuthorities());
        oauthClientDetails.setAccessTokenValidity(this.clientDetailsProp.getAccessTokenValidity());
        oauthClientDetails.setRefreshTokenValidity(this.clientDetailsProp.getRefreshTokenValidity());
        oauthClientDetails.setAutoapprove(oauthClientDetails.getScope()); //与scope相同
        this.oauthClientDetailsService.insertOne(oauthClientDetails);
        return "注册成功";
    }
}
