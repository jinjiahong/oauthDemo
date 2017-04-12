package demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager; //在OAuth2SecurityConfiguration

	@Autowired
	private DataSource dataSource;


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//=============存库===========
//		clients.jdbc(dataSource)
//				.withClient("acme")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//				.scopes("read", "write", "trust")
//				.secret("acme")
//				.accessTokenValiditySeconds(600) //指定任何生成的访问令牌的有效期只有600秒；
//				.refreshTokenValiditySeconds(6000) //指定任何刷新生成令牌的有效期只有6000秒
//				.autoApprove(true);
		clients.jdbc(dataSource);//会直接检测数据库中oauthClientDetails表内信息
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler) //userApprovalHandler 在OAuth2SecurityConfiguration
				.authenticationManager(authenticationManager);
//		endpoints
//				.tokenStore(tokenStore)
//				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}

}