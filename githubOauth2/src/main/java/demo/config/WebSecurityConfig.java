package demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用第三方授权（微博）客户端demo[不是授权服务器]
 * Created by JJH on 2017/4/6.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    /**
     * 主过滤器(addFilterBefore中调用了第三方过滤器)
     * @param http http请求的
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**").authorizeRequests() //对${context}下的所有URL进行授权
                //"/","/login**","/webjars/**"请求所有人都能访问，除此之外别的请求都需要oauth2认证
                .antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
                .authenticated().and().exceptionHandling() //配置异常处理页面为“/”
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
                .logoutSuccessUrl("/").permitAll().and().csrf()  //配置logout成功后跳转页面“/”,并配置“/”请求所有人都能访问
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);//认证之前加上sso过滤器，sso过滤器中获取拼接具体参数带入github的认证接口
        // @formatter:on
    }

    /**
     * 在内存中创建了一个用户，该用户的名称为admin，密码为123456，用户角色为USER。
     * @param auth
     * @throws Exception
     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("USER");
//    }

    /**
     * 第三方过滤器，需要加到主过滤器
     * 检查请求的地址，符合"/login/facebook"或者"/login/github"则执行第二个ssoFilter
     * @return 返回CompositeFilter 过滤器
     */
    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    //跳转在配置文件中配置的client信息，进行第三方授权认证
    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = null;
        filter = new OAuth2ClientAuthenticationProcessingFilter(
                path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

}
