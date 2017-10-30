package com.ecochain.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ecochain.service.AclUserService;
import com.ecochain.user.entity.AclUser;
import com.ecochain.user.entity.UserConstatnt;
import com.fasterxml.jackson.databind.ObjectMapper;


/** 
 * @Description: 类说明: Security配置类
 * @author: xiong  
 * @GitConfig name: xiong  email: shao200815@163.com
 * @date: 2017-10-30 15:11:54 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomSecurityFilter customSecurityFilter;

	//messageSource 在MessageSource里定义的
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private AclUserService aclUserService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterAt(concurrentSessionFilter(), ConcurrentSessionFilter.class)
            .addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(customSecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器  
            .authorizeRequests()
                //问号需要转译，不使用antMatcers，是因为不知道ant里?怎么转译
                .regexMatchers("/login\\?invalid", "/login\\?expired",
                        "/index", "/home", "/getToken", "/accessDenied"
                        , "/changeLang")
                .permitAll()//访问：/home 无需登录认证权限  
                .anyRequest()
                .authenticated() //其他所有资源都需要认证，登陆后访问  
            .and()
                //有关fornLogin的设置
                //https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#nsa-form-login-parents
                .formLogin()
                    .loginPage("/login")//用于呈现登录页面的URL。默认为“/ login”。
                    .permitAll()
            .and()
                .sessionManagement()
                .invalidSessionUrl("/login?invalid")//sessoin超时的响应url
            .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
            .and()
                .logout()
                    .invalidateHttpSession(false)
                    .logoutUrl("/logout")
                    //定义logoutSeccessHandler后logouturl和logoutsuccessurl无效，
                    //需要在logoutSeccessHandler中定义
//                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            .and()
                .csrf().disable();
            
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // js 、 css 、等不做权限验证
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", 
                "/**/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customProvider()); // 配置登录provider
        auth.authenticationProvider(new AnonymousAuthenticationProvider("work"));
        auth.authenticationEventPublisher(new DefaultAuthenticationEventPublisher());
    }
    
    @Bean
    public CustomProvider customProvider(){
        CustomProvider provider = new CustomProvider();
        provider.setUserDetailsService(userDetailsService);
        
        //密码加盐
        /*
         * 可以是方法名，也可以是bean属性名。如果您的UserDetails包含一个UserDetails.getSalt()方法，
         * 您应该将该属性设置为“getSalt”或“salt”。
         * 
         * 确保salt值是不会被修改的、例如用户ID
         * 确保salt值是不会被修改的、例如用户ID
         * 确保salt值是不会被修改的、例如用户ID
         */
//        ReflectionSaltSource saltSource = new ReflectionSaltSource();
//        saltSource.setUserPropertyToUse("username");
        /*
         * 如果使用BCryptPasswordEncoder 加密方式，则不需要设置salt，BCryptPasswordEncoder 为官方推荐使用加密方式
         * 因为BCryptPasswordEncoder内置随机生成salt，即使同一个密码，每次生成也不同，请查看PassWordTest
         * 
         * 如果不使用BCryptPasswordEncoder，则请设置salt增加安全性
         * provider.setSaltSource(saltSource);
         */
        
        //TODO 设置密码加密方式
        provider.setPasswordEncoder(passwordEncoder());
        
        provider.setMessageSource(messageSource);
        return provider;
    }
    
    @Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder(4);  
    }  
    
	@Bean
    UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setPostOnly(true);
        filter.setAuthenticationManager(customSecurityFilter.getAuthenticationManager());
        //设置登录请求的链接为/login post 请求
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        //form表达对应的key
        filter.setUsernameParameter("username"); 
        filter.setPasswordParameter("password");
        filter.setMessageSource(messageSource);
        //登录成功或登录失败对应的链接或handler
        filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler("/hello", true));
        filter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy ());
        return filter;
    }
    
    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
        SessionInformationExpiredStrategy expiredStrategy = 
                //session被踢出时响应url
                new SimpleRedirectSessionInformationExpiredStrategy("/login?expired");
        return new ConcurrentSessionFilter(sessionRegistry(),expiredStrategy);
    }
	
	private SessionAuthenticationStrategy sessionAuthenticationStrategy() {
	    List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<SessionAuthenticationStrategy>();
	    //用户登录时注册一个新的session 否则无法踢出用户
	    delegateStrategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry()));
	    
	    ConcurrentSessionControlAuthenticationStrategy concurrent = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
	    concurrent.setMaximumSessions(1);//一个用户可以同时在存在几个session
	    concurrent.setExceptionIfMaximumExceeded(false);//当session达到最大时是否还允许其他用户登录
	    delegateStrategies.add(concurrent);
        return new CompositeSessionAuthenticationStrategy(delegateStrategies );
    }

	@Bean(name = "failureHandler")
    public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        // post登陆请求失败后返回页面
        SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler
                ("/login?error");
        //如果设置为true，则对失败目的地URL执行转发，而不是重定向。默认值为false。
//        simpleUrlAuthenticationFailureHandler.setUseForward(true);
        
        return simpleUrlAuthenticationFailureHandler;
    }

    class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
        
        CustomAuthenticationSuccessHandler(String defaultTargetUrl, boolean alwaysUseDefaultTargetUrl){
            setDefaultTargetUrl(defaultTargetUrl);
            setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
        }
        
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request,
	                                        HttpServletResponse response,
	                                        Authentication authentication) throws ServletException, IOException {
	        
	        //更新用户信息，充值密码错误次数
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            
            AclUser aclUser = aclUserService.findAclUserByName(securityUser.getUsername());
            
            aclUser.setLastModifyTime(new Date());
            aclUser.setIslock(UserConstatnt.ACLUSER_ISLOCK_NO);
            aclUser.setFailcount(0);
            aclUserService.updateUserEntity(aclUser);
	        
	        //方式一： 返回json数据，
//	      //例1：不跳到XML设定的页面，而是直接返回json字符串
//	        response.setCharacterEncoding("UTF-8");
//	        response.setContentType("application/json");
//	        response.setHeader("Access-Control-Allow-Origin", "*");
//	        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//	        HashMap<String, Object> map = new HashMap<String, Object>();
//	        map.put("realname", securityUser.getRealname());
//	        map.put("peopletype", securityUser.getPeopletype());
//	        map.put("username", securityUser.getUsername());
//	        map.put("mobile", securityUser.getMobile());
//	        map.put("code", 200);
//	        map.put("message", "success");
//	        ObjectMapper mapper = new ObjectMapper();
//	        response.getWriter().println(mapper.writeValueAsString(map));
	        
	        //方式二：转发到指定url
	        super.onAuthenticationSuccess(request, response, authentication);
	    }

        @Override
        public void setDefaultTargetUrl(String defaultTargetUrl) {
            super.setDefaultTargetUrl(defaultTargetUrl);
        }

        @Override
        public void setAlwaysUseDefaultTargetUrl(
                boolean alwaysUseDefaultTargetUrl) {
            super.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
        }
	}
	
    class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setHeader("Access-Control-Allow-Origin", "*");
                HashMap<String, Object> map = new HashMap<String, Object>();
                String sessionId=request.getRequestedSessionId();
                map.put("code", 200);
                map.put("message", "success");
                map.put("sessionId", sessionId);
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().println(mapper.writeValueAsString(map));
        }
    }

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
