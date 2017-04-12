package demo.security;

import demo.prop.JdbcContextConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private JdbcContextConfig jdbcContextConfig;

	private static final String RESOURCE_ID = "my_api";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenServices(tokenServices()).resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/jjh/**")
		.and().authorizeRequests()
		.antMatchers("/jjh/**").access("hasRole('ROLE_ADMIN')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		// @formatter:on
	}

	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcContextConfig.getDriverClassName());
		dataSource.setUrl(jdbcContextConfig.getUrl());
		dataSource.setUsername(jdbcContextConfig.getUser());
		dataSource.setPassword(jdbcContextConfig.getPass());
		return dataSource;
	}

}