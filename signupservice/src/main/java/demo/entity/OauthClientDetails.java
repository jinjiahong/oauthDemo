package demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 实体
 * Created by JJH on 2017/4/10.
 */
@Data
public class OauthClientDetails implements Serializable{
    private static final long serialVersionUID = 5779619887288863412L;
    @Id
    private String clientId;
    @Column
    private String resourceIds;
    @Column
    private String clientSecret;
    @Column
    private String scope;
    @Column
    private String authorizedGrantTypes;
    @Column
    private String webServerRedirectUri;
    @Column
    private String authorities;
    @Column
    private int accessTokenValidity;
    @Column
    private int refreshTokenValidity;
    @Column
    private String additionalInformation;
    @Column
    private String autoapprove;
}
