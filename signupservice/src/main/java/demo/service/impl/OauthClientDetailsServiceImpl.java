package demo.service.impl;

import demo.entity.OauthClientDetails;
import demo.repository.OauthClientDetailsRepository;
import demo.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * oauthclientdetails服务类的实现类
 * Created by JJH on 2017/4/13.
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService{

    private OauthClientDetailsRepository clientDetailsRepository;

    @Autowired
    public OauthClientDetailsServiceImpl(OauthClientDetailsRepository clientDetailsRepository){
        this.clientDetailsRepository = clientDetailsRepository;
    }

    @Override
    public void insertOne(OauthClientDetails clientDetails) {
        this.clientDetailsRepository.insert(clientDetails);
    }

    /**
     * 根据主键客户端id去查询，查询到返回true
     * @param clientId 客户端id
     * @return 返回boolean
     */
    @Override
    public boolean isExist(String clientId) {
        boolean flag = false;
        OauthClientDetails oauthClientDetails = this.clientDetailsRepository.selectByPrimaryKey(clientId);
        if(null != oauthClientDetails){
            flag = true;
        }
        return flag;
    }
}
