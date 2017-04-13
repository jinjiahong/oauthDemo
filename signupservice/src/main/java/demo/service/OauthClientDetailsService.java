package demo.service;

import demo.entity.OauthClientDetails;

/**
 * oauthClientDetail表的服务
 * Created by JJH on 2017/4/13.
 */
public interface OauthClientDetailsService {
    void insertOne(OauthClientDetails clientDetails);
    boolean isExist(String clientId);
}
